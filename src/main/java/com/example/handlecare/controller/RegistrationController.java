package com.example.handlecare.controller;

import com.example.handlecare.dto.UserDto;
import com.example.handlecare.entity.User;
import com.example.handlecare.security.token.ConfirmationToken;
import com.example.handlecare.service.dbServices.ConfirmationTokenServiceImpl;
import com.example.handlecare.service.dbServices.RegistrationService;
import com.example.handlecare.service.dbServices.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;


@Controller("registration")
public class RegistrationController {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    ConfirmationTokenServiceImpl tokenService;
    @Autowired
    RegistrationService registrationService;

    @GetMapping("registration")
    public String registration() {return "registration";}

    @PostMapping("/register")
    public String register(@ModelAttribute User user,
                           @Valid UserDto dto,
                           Errors errors,
                           Model model) {
        try{
            User registered = userService.registerNewUserAccount(dto);
            ConfirmationToken confirmationToken = tokenService.saveAndBondToken(registered);
               registrationService.sendEmail(dto, confirmationToken);
            model.addAttribute("user", registered);
            return "redirect:/authorization";
        }catch (Exception e){
            System.out.println(e.toString());
            model.addAttribute("message", e.getMessage());
            return "registration";
        }
    }

    @GetMapping("registration/confirm")
    public String confirm(@RequestParam("token") String token, Model model) {
        registrationService.confirmToken(token);
        model.addAttribute("message", "Вам на почту будет отправлен код подтверждения");
        return "redirect:/authorization";
    }
}
