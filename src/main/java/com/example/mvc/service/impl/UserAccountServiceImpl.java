package com.example.mvc.service.impl;

import com.example.mvc.model.ConfirmationToken;
import com.example.mvc.model.User;
import com.example.mvc.repository.ConfirmationTokenRepository;
import com.example.mvc.repository.UserAccountRepository;
import com.example.mvc.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    @Override
    public User userRegistration(User user) {
        User users = userAccountRepository.save(user);
        String randomToken = RandomString.make(60);
        ConfirmationToken verificationToken = new ConfirmationToken();
        verificationToken.setUser(user);
        verificationToken.setToken(randomToken);
        confirmationTokenRepository.save(verificationToken);
        System.out.println(verificationToken);
        return users;
    }
}
