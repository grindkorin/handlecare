package com.example.handlecare.controller;

import com.example.handlecare.dto.UserDto;
import com.example.handlecare.entity.User;
import com.example.handlecare.service.dbServices.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller("registration")
public class RegistrationController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("registration")
    public String registration() {return "registration";}

    @PostMapping("/register")
    public String register(@ModelAttribute User user,
                           @Valid UserDto dto,
                           Errors errors,
                           Model model) {
        try{
            User registered = userService.registerNewUserAccount(dto);
            model.addAttribute("user", registered);
            return "redirect:/authorization";
        }catch (Exception e){
            System.out.println(e.toString());
            model.addAttribute("message", e.getMessage());
            return "registration";
        }
    }
}