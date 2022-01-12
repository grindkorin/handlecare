package com.example.handlecare.repository;

import com.example.handlecare.entity.Deliver;
import com.example.handlecare.entity.Order;
import com.example.handlecare.entity.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliverRepository extends JpaRepository<Deliver, Integer> {
    Deliver findByLogin(String login);
    Deliver findByEmail(String email);
    Deliver findByPhoneNumber(String phoneNumber);
    List<Deliver> findAllByStatus(Status status);
}
