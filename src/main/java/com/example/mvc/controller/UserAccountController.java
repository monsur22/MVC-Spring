package com.example.mvc.controller;

import com.example.mvc.model.User;
import com.example.mvc.repository.UserAccountRepository;
import com.example.mvc.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserAccountController {
    public final UserAccountService userAccountService;
    public final UserAccountRepository userAccountRepository;

    private final String userSignupPage = "sign_up";
    private final String userSigninPage = "sign_in";
    private final String redirectPage = "redirect:/singin";
    private final String homePage = "redirect:/";

    @GetMapping(WebConstants.SING_UP)
    public String getUserSignupPage(User user, Model model) {
        return userSignupPage;
    }

    @PostMapping(WebConstants.USER_DATA_STORE)
    public String storeUserData(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
        user = userAccountService.userRegistration(user);
        if (user.getIsActive()) {
            redirectAttributes.addFlashAttribute("message", "Email Address Already Taken");
        } else {
            redirectAttributes.addFlashAttribute("message", "Check Your Email");
        }
        return redirectPage;

    }

    @GetMapping(WebConstants.USER_CONFIRMATION)
    public String confirmUserAccount(@PathVariable String token, RedirectAttributes redirectAttributes) {
        Boolean user = userAccountService.confirmUserAccount(token);
        if (user) {
            redirectAttributes.addFlashAttribute("success", "User Account Verified");
            return redirectPage;
        }
        redirectAttributes.addFlashAttribute("danger", "User Account Not Verified");
        return redirectPage;
    }

    @GetMapping(WebConstants.SING_IN)
    public String getUserSingingPage(User user, Model model) {
        return userSigninPage;
    }

    @PostMapping(WebConstants.USER_LOGIN)
    public String userLogin(@ModelAttribute("users") User user, HttpSession session, RedirectAttributes redirectAttributes) {
        Boolean getUser = userAccountService.loginUserAccount(user);
        if (getUser) {
            session.setAttribute("useremail",user.getEmail());
            redirectAttributes.addFlashAttribute("success", "User Login Successfully");
            return homePage;
        }
        redirectAttributes.addFlashAttribute("danger", "Email or Password not match");
        return redirectPage;
    }
}
