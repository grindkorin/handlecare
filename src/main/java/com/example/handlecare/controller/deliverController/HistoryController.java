package com.example.handlecare.controller.deliverController;

import com.example.handlecare.entity.Deliver;
import com.example.handlecare.entity.Order;
import com.example.handlecare.entity.Recipient;
import com.example.handlecare.entity.enums.Progression;
import com.example.handlecare.service.dbServices.DeliverServiceImpl;
import com.example.handlecare.service.dbServices.OrderServiceImpl;
import com.example.handlecare.service.dbServices.RecipientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller("/deliver/history")
public class HistoryController {

    final
    OrderServiceImpl orderService;
    final
    DeliverServiceImpl deliverService;
    final
    RecipientServiceImpl recipientService;

    public HistoryController(OrderServiceImpl orderService, DeliverServiceImpl deliverService, RecipientServiceImpl recipientService) {
        this.orderService = orderService;
        this.deliverService = deliverService;
        this.recipientService = recipientService;
    }

    @GetMapping("/deliver/history")
    public String history(Authentication authentication, ModelMap model) {
        List<Order> orders = orderService.findAllByDeliverAndProgression(
               deliverService.findByLogin(authentication.getName()), Progression.COMPLETE);
        List<Recipient> recipients = orders.stream().map(Order::getRecipient).collect(Collectors.toList());
        if(!orders.isEmpty())
        model.put("orders", orders);
        model.put("recipients", recipients);
        return "/deliver/history";
    }
}
