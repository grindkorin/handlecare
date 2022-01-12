package com.example.handlecare.controller.deliverController;

import com.example.handlecare.entity.Order;
import com.example.handlecare.entity.Recipient;
import com.example.handlecare.entity.enums.Progression;
import com.example.handlecare.service.dbServices.DeliverServiceImpl;
import com.example.handlecare.service.dbServices.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.handlecare.entity.enums.Progression.*;
import static com.example.handlecare.entity.enums.Progression.COMPLETE;
import static com.example.handlecare.entity.enums.Progression.INACTION;

@Controller("/deliver/requests")
public class RequestsController {

    final
    OrderServiceImpl orderService;
    final
    DeliverServiceImpl deliverService;

    public RequestsController(OrderServiceImpl orderService, DeliverServiceImpl deliverService) {
        this.orderService = orderService;
        this.deliverService = deliverService;
    }


    @GetMapping("/deliver/requests")
    public String active(ModelMap model) {
        //TODO получение пользователя из сессии
        List<Order> orders = orderService.findAllByProgression(FRESH);
        List<Recipient> recipients = orders.stream().map(Order::getRecipient).collect(Collectors.toList());
        if(!orders.isEmpty())
        model.put("orders", orders);
        model.put("recipients", recipients);
        return "/deliver/requests";
    }

    @PostMapping(value = "/deliverRequestOrder/{id}")
    public String confirmOrder(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Order order = orderService.getById(id);
        order.setProgression(INACTION);
        order.setDeliver(deliverService.getById(1)); // - todo session
        orderService.save(order);
        return "redirect:/deliver/requests";
    }
}
