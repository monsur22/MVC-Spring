package com.example.mvc.service;

import com.example.mvc.model.User;

public interface UserAccountService {

    User userRegistration(User user);

    String registrationEmailSend(User user, String verificationToken);

    Boolean confirmUserAccount(String token);

    Boolean loginUserAccount(User user);
}
