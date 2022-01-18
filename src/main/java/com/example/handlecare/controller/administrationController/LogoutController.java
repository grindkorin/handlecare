package com.example.handlecare.controller.administrationController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("administration/logout")
public class LogoutController {


    @GetMapping("/administration/logout")
    public String logout() {return "/administration/logout";}
}
