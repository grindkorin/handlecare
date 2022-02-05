package com.example.handlecare.controller;

import com.example.handlecare.dto.UserDto;
import com.example.handlecare.entity.User;
import com.example.handlecare.service.dbServices.ConfirmationTokenServiceImpl;
import com.example.handlecare.service.dbServices.ForgotPasswordService;
import com.example.handlecare.service.dbServices.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.support.SessionFlashMapManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
