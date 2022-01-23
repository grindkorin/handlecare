package com.example.handlecare.controller.personalControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/personal/logout")
public class LogoutController {

    //TODO логаут в секьюр
    @GetMapping("/personal/logout")
    public String logout() {return "/logout";}
}
