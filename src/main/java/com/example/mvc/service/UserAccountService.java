package com.example.mvc.service;

import com.example.mvc.model.ConfirmationToken;
import com.example.mvc.model.User;

import java.util.Optional;

public interface UserAccountService {

    User userRegistration(User user);
    String registrationEmailSend(User user, String  verificationToken);
    Optional<ConfirmationToken> getConfirmationToken(String token);
}
