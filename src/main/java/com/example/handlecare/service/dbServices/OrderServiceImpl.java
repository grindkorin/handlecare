package com.example.handlecare.service.dbServices;

import com.example.handlecare.entity.Deliver;
import com.example.handlecare.entity.Order;
import com.example.handlecare.entity.Recipient;
import com.example.handlecare.entity.enums.Progression;
import com.example.handlecare.repository.OrderRepository;
import com.example.handlecare.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    final
    OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findAllByProgression(Progression progression) {
        return orderRepository.findAllByProgression(progression);
    }

    @Override
    public List<Order> findAllByRecipientAndProgression(Recipient recipient, Progression progression) {
        return orderRepository.findAllByRecipientAndProgression(recipient, progression);
    }

    @Override
    public List<Order> findAllByDeliverAndProgression(Deliver deliver, Progression progression) {
        return orderRepository.findAllByDeliverAndProgression(deliver, progression);
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order getById(Integer id) {
        return orderRepository.getById(id);
    }

    @Override
    public List<Order> saveAll(List<Order> orders) {
        return orderRepository.saveAll(orders);
    }
}
