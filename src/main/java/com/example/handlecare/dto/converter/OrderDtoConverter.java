package com.example.handlecare.dto.converter;

import com.example.handlecare.dto.OrderDto;
import com.example.handlecare.entity.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderDtoConverter {
    public OrderDto toDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setItem(order.getItem());
        dto.setMarket(order.getMarket());
        dto.setAmount(order.getAmount());
        dto.setProgression(order.getProgression());
        return dto;
    }

    public List<OrderDto> allToDto(List<Order> orders) {
        return orders.stream().map(this::toDto).collect(Collectors.toList());
    }

    public Order fromDto(OrderDto dto) {
        Order order = new Order();
        order.setId(dto.getId());
        order.setItem(dto.getItem());
        order.setMarket(dto.getMarket());
        order.setAmount(dto.getAmount());
        order.setProgression(dto.getProgression());
        return order;
    }

    public List<Order> allFromDto(List<OrderDto> orders) {
        return orders.stream().map(this::fromDto).collect(Collectors.toList());
    }
}
