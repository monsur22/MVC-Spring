package com.example.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserAccountController {
    private final String userSignupPage = "sign_up";
    private final String userSigninPage = "sign_in";

    @GetMapping(WebConstants.SING_UP)
    public String getUserSignupPage(Model model) {
        return userSignupPage;
    }

    @GetMapping(WebConstants.SING_IN)
    public String getUserSingingPage(Model model) {
        return userSigninPage;
    }
}
