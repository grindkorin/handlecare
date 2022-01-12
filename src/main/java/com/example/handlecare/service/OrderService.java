package com.example.handlecare.service;

import com.example.handlecare.entity.Deliver;
import com.example.handlecare.entity.Order;
import com.example.handlecare.entity.Recipient;
import com.example.handlecare.entity.enums.Progression;

import java.util.List;

public interface OrderService {
    Order getById(Integer id);
    List<Order> findAll();
    List<Order> findAllByProgression(Progression progression);
    List<Order> findAllByRecipientAndProgression(Recipient recipient, Progression progression);
    List<Order> findAllByDeliverAndProgression(Deliver deliver, Progression progression);
    Order save(Order order);
    List<Order> saveAll(List<Order> orders);
}
