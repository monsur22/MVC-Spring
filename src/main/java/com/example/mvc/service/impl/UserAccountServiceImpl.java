package com.example.mvc.service.impl;

import com.example.mvc.model.ConfirmationToken;
import com.example.mvc.model.User;
import com.example.mvc.repository.ConfirmationTokenRepository;
import com.example.mvc.repository.UserAccountRepository;
import com.example.mvc.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public User userRegistration(User user) {
        User users = userAccountRepository.save(user);
        String randomToken = RandomString.make(60);
        ConfirmationToken verificationToken = new ConfirmationToken();
        verificationToken.setUser(user);
        verificationToken.setToken(randomToken);
        confirmationTokenRepository.save(verificationToken);
        registrationEmailSend(user, verificationToken.getToken());
        return users;
    }

    @Override
    public String registrationEmailSend(User user, String verificationToken) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(user.getEmail());
            mailMessage.setText("\r\n" + "http://localhost:8080/user/confirmation/" + verificationToken);
            mailMessage.setSubject("User Registration");
            javaMailSender.send(mailMessage);
            return "Mail sent SuccessFully...";
        } catch (Exception e) {
            System.out.println(user.getEmail());
            return "Error while sending Mail";
        }
    }

    @Override
    public Optional<ConfirmationToken> getConfirmationToken(String token){
        return confirmationTokenRepository.findConfirmationTokenByToken(token);
    }
}
