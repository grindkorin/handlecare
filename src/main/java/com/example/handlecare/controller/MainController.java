package com.example.handlecare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String base(ModelMap modelMap) {
        return "redirect:home";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("recipient")
    public String recipient() {
        return "recipient";
    }

    @GetMapping("deliverInfo")
    public String deliver() {
        return "deliverInfo";
    }


    @GetMapping("authorization")
    public String authorization() {return "authorization";}

    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }

}

