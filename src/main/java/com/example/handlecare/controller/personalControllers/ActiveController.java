package com.example.handlecare.controller.personalControllers;

import com.example.handlecare.entity.Deliver;
import com.example.handlecare.entity.Order;
import com.example.handlecare.entity.enums.Progression;
import com.example.handlecare.service.dbServices.DeliverServiceImpl;
import com.example.handlecare.service.dbServices.OrderServiceImpl;
import com.example.handlecare.service.dbServices.RecipientServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller("/personal/active")
public class ActiveController {

    final
    OrderServiceImpl orderService;
    final
    RecipientServiceImpl recipientService;
    final
    DeliverServiceImpl deliverService;

    public ActiveController(DeliverServiceImpl deliverService, OrderServiceImpl orderService, RecipientServiceImpl recipientService) {
        this.deliverService = deliverService;
        this.orderService = orderService;
        this.recipientService = recipientService;
    }

    @GetMapping("/personal/active")
    public String active(Authentication authentication, ModelMap model) {
        //TODO получение пользователя из сессии
        List<Order> orders = orderService.findAllByRecipientAndProgression(
                recipientService.findByLogin(authentication.getName()), Progression.INACTION);
        List<Deliver> delivers = orders.stream().map(Order::getDeliver).collect(Collectors.toList());
        if(!orders.isEmpty())
        model.put("orders", orders);
        model.put("delivers", delivers);
        return "/personal/active";
    }

}
