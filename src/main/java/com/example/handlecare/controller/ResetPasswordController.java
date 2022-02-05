package com.example.handlecare.controller;

import com.example.handlecare.service.dbServices.ConfirmationTokenServiceImpl;
import com.example.handlecare.service.dbServices.ForgotPasswordService;
import com.example.handlecare.service.dbServices.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller("reset_password")
public class ResetPasswordController {

    @Autowired
    ForgotPasswordService forgotPasswordService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    ConfirmationTokenServiceImpl tokenService;

//    @GetMapping("reset_password")
//    public String resetPassword() {
//        return "reset_password";
//    }


    // @GetMapping("forgot_password/confirm")
    @GetMapping("reset_password")
    public String confirm(@RequestParam("token") String token,
                          Model model) {
        forgotPasswordService.resetPasswordConfirmToken(token);
        model.addAttribute("token", token);
        return "reset_password";
    }

    @RequestMapping("/resetPassword")
    public String resetPass(Model model,
                            HttpServletRequest request) {
        forgotPasswordService.resetPassword(request.getParameter("token"),
                                            request.getParameter("password"));
        return "/authorization";
    }
}
