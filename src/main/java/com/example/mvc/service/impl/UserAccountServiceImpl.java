package com.example.mvc.service.impl;

import com.example.mvc.model.ConfirmationToken;
import com.example.mvc.model.User;
import com.example.mvc.repository.ConfirmationTokenRepository;
import com.example.mvc.repository.UserAccountRepository;
import com.example.mvc.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    @Transactional
    public User userRegistration(User user) {
        Optional<User> userData = userAccountRepository.findUserByEmail(user.getEmail());
        User finalUser = user;
        userData.ifPresent(userObj -> {
            if(!userObj.getIsActive()){
                // If user found delete the Old token
                ConfirmationToken existToken = confirmationTokenRepository.findByUserId(userObj.getId());
                confirmationTokenRepository.delete(existToken);
                // Update if name and password field change again
                User updateUser = existToken.getUser();
                updateUser.setPassword(finalUser.getPassword());
                updateUser.setName(finalUser.getName());
                userAccountRepository.save(updateUser);
                // then insert again and send email
                ConfirmationToken verificationToken = confirmationTokenGenerate(userObj);
                registrationEmailSend(userObj, verificationToken.getToken());
            }
        });
        if(userData.isEmpty()) {
            user  = userAccountRepository.save(user);
            ConfirmationToken verificationToken = confirmationTokenGenerate(user);
            registrationEmailSend(user, verificationToken.getToken());
        }
        return userData.isPresent() && userData.get().getIsActive() ? userData.get() : user;
    }

    @Async
    @Override
    public void registrationEmailSend(User user, String verificationToken) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(user.getEmail());
            mailMessage.setText("\r\n" + "http://localhost:8080/user/confirmation/" + verificationToken);
            mailMessage.setSubject("User Registration");
            javaMailSender.send(mailMessage);
            log.info("Email Send Successfully.");
        } catch (Exception e) {
            log.error("Email Send fail.");
        }
    }

    @Override
    public Boolean confirmUserAccount(String token) {
        Optional<ConfirmationToken> confirmToken = confirmationTokenRepository.findConfirmationTokenByToken(token);
        if (confirmToken.isPresent()) {
            User user = confirmToken.get().getUser();
            user.setIsActive(true);
            userAccountRepository.save(user);
            confirmationTokenRepository.delete(confirmToken.get());
            return true;
        }
        return false;
    }

    @Override
    public Boolean loginUserAccount(User user) {
        Optional<User> getUser = userAccountRepository.findUserByEmail(user.getEmail());
        if (getUser.isPresent() && getUser.get().getIsActive()!=false) {
            if (Objects.equals(user.getPassword(), getUser.get().getPassword())){
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public ConfirmationToken confirmationTokenGenerate(User user) {
        String randomToken = RandomString.make(60);
        ConfirmationToken verificationToken = new ConfirmationToken();
        verificationToken.setUser(user);
        verificationToken.setToken(randomToken);
        return confirmationTokenRepository.save(verificationToken);
    }
}
