package com.example.handlecare.repository;

import com.example.handlecare.entity.Deliver;
import com.example.handlecare.entity.Order;
import com.example.handlecare.entity.Recipient;
import com.example.handlecare.entity.enums.Progression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByProgression(Progression progression);
    List<Order> findAllByRecipientAndProgression(Recipient recipient, Progression progression);
    List<Order> findAllByDeliverAndProgression(Deliver deliver, Progression progression);
    List<Order> findAllByRecipientAndDeliver(Recipient recipient, Deliver deliver);
}
