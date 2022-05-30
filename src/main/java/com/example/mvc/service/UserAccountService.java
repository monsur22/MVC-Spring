package com.example.mvc.service;

import com.example.mvc.model.ConfirmationToken;
import com.example.mvc.model.User;

public interface UserAccountService {

    User userRegistration(User user);

    void registrationEmailSend(User user, String verificationToken);

    Boolean confirmUserAccount(String token);

    Boolean loginUserAccount(User user);

    ConfirmationToken confirmationTokenGenerate(User user);
}
