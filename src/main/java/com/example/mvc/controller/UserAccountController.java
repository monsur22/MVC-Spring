package com.example.mvc.controller;

import com.example.mvc.model.User;
import com.example.mvc.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserAccountController {
    public final UserAccountService userAccountService;

    private final String userSignupPage = "sign_up";
    private final String userSigninPage = "sign_in";
    private final String redirectPage = "redirect:/singup";

    @GetMapping(WebConstants.SING_UP)
    public String getUserSignupPage(User user, Model model) {
        return userSignupPage;
    }

    @PostMapping(WebConstants.USER_DATA_STORE)
    public String storeUserData(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
        userAccountService.userRegistration(user);
        redirectAttributes.addFlashAttribute("success", "User Registration Successfully");
        return redirectPage;

    }

    @GetMapping(WebConstants.SING_IN)
    public String getUserSingingPage(Model model) {
        return userSigninPage;
    }
}
