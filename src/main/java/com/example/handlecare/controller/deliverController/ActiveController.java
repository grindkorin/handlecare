package com.example.handlecare.controller.deliverController;

import com.example.handlecare.entity.Order;
import com.example.handlecare.entity.Recipient;
import com.example.handlecare.entity.enums.Progression;
import com.example.handlecare.service.dbServices.DeliverServiceImpl;
import com.example.handlecare.service.dbServices.OrderServiceImpl;
import com.example.handlecare.service.dbServices.RecipientServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.handlecare.entity.enums.Progression.COMPLETE;

@Controller("/deliver/active")
public class ActiveController {

    final
    OrderServiceImpl orderService;
    final
    DeliverServiceImpl deliverService;
    final
    RecipientServiceImpl recipientService;

    public ActiveController(OrderServiceImpl orderService, DeliverServiceImpl deliverService, RecipientServiceImpl recipientService) {
        this.orderService = orderService;
        this.deliverService = deliverService;
        this.recipientService = recipientService;
    }


    @GetMapping("/deliver/active")
    public String active(Authentication authentication, ModelMap model) {
        List<Order> orders = orderService.findAllByDeliverAndProgression(
                deliverService.findByLogin(authentication.getName()), Progression.INACTION);
        List<Recipient> recipients = orders.stream().map(Order::getRecipient).collect(Collectors.toList());
        if(!orders.isEmpty())
        model.put("orders", orders);
        model.put("recipients", recipients);
        return "/deliver/active";
    }


    @PostMapping(value = "/deliverActiveOrder/{id}")
    public String confirmOrder(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Order order = orderService.getById(id);
        order.setProgression(COMPLETE);
        orderService.save(order);
        return "redirect:/deliver/active";
    }

}
