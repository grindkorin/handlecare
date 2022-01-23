package com.example.handlecare.controller.deliverController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/deliver/logout")
public class LogoutController {

    //TODO логаут в секьюр
    @GetMapping("/deliver/logout")
    public String logout() {return "/logout";}
}
