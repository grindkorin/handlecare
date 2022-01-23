package com.example.handlecare.controller.personalControllers;


import com.example.handlecare.entity.Order;
import com.example.handlecare.entity.Recipient;
import com.example.handlecare.entity.enums.Progression;
import com.example.handlecare.service.dbServices.OrderServiceImpl;
import com.example.handlecare.service.dbServices.RecipientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller("/personal/newOrder")
public class NewOrderController {
    final
    OrderServiceImpl orderService;
    final
    RecipientServiceImpl recipientService;

    public NewOrderController(OrderServiceImpl orderService, RecipientServiceImpl recipientService) {
        this.orderService = orderService;
        this.recipientService = recipientService;
    }

    @GetMapping("/personal/newOrder")
    public String newOrder() {
        return "/personal/newOrder";
    }

    @PostMapping("/postOrder")
    public String postOrder(@ModelAttribute Order order, Authentication authentication,
                            Model model) {
        order.setAmount(25.25f);
        order.setProgression(Progression.FRESH);
        Recipient recipient = recipientService.findByLogin(authentication.getName());
        order.setRecipient(recipient);
        orderService.save(order);
        model.addAttribute(order);
        return "redirect:/personal/newOrder";
    }
}
