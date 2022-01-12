package com.example.handlecare.controller.personalControllers;

import com.example.handlecare.entity.Deliver;
import com.example.handlecare.entity.Order;
import com.example.handlecare.entity.enums.Progression;
import com.example.handlecare.service.dbServices.DeliverServiceImpl;
import com.example.handlecare.service.dbServices.OrderServiceImpl;
import com.example.handlecare.service.dbServices.RecipientServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;
import java.util.stream.Collectors;

@Controller("/personal/history")
public class HistoryController {

    final
    DeliverServiceImpl deliverService;
    final
    RecipientServiceImpl recipientService;
    final
    OrderServiceImpl orderService;

    public HistoryController(DeliverServiceImpl deliverService, RecipientServiceImpl recipientService, OrderServiceImpl orderService) {
        this.deliverService = deliverService;
        this.recipientService = recipientService;
        this.orderService = orderService;
    }

    @GetMapping("/personal/history")
    public String active(ModelMap model) {
        //TODO получение пользователя из сессии
        List<Order> orders = orderService.findAllByRecipientAndProgression(recipientService.getById(1), Progression.COMPLETE);
        List<Deliver> delivers = orders.stream().map(Order::getDeliver).collect(Collectors.toList());
        if(!orders.isEmpty())
        model.put("orders", orders);
        model.put("delivers", delivers);
        return "/personal/history";
    }
}
