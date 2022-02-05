package com.example.handlecare.controller;

import com.example.handlecare.entity.User;
import com.example.handlecare.service.dbServices.ConfirmationTokenServiceImpl;
import com.example.handlecare.service.dbServices.ForgotPasswordService;
import com.example.handlecare.service.dbServices.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller("forgot_password")
public class ForgotPasswordController {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    ConfirmationTokenServiceImpl tokenService;
    @Autowired
    ForgotPasswordService forgotPasswordService;


    @GetMapping("forgot_password")
    public String forget() {
        return "forgot_password";
    }

    @PostMapping("/forgot")
    public String forgot(@ModelAttribute User user,
                         Model model) {
        try {
            forgotPasswordService.forgetPassword(user.getEmail());
            model.addAttribute("message", "Ссылка выслана вам на почту");
            return "/forgot_password";
        } catch (Exception e) {
            System.out.println(e.toString());
            model.addAttribute("message", e.getMessage());
            return "/authorization";
        }
    }
//
//    @GetMapping("forgot_password/confirm")
//    public String confirm(@RequestParam("token") String token, @ModelAttribute ("email") String email, RedirectAttributes redirectAttributes ) {
//        forgotPasswordService.resetPasswordConfirmToken(token);
//        User user = tokenService.findByToken(token).getDeliver();
//        if(user != null) {
//            redirectAttributes.addFlashAttribute("email", user.getEmail());
//            System.out.println(user);
//        } else if(tokenService.findByToken(token).getRecipient() != null) {
//            user = tokenService.findByToken(token).getRecipient();
//            redirectAttributes.addFlashAttribute("email", user.getEmail());
//        }
//        return "redirect:/reset_password";
//    }
}
