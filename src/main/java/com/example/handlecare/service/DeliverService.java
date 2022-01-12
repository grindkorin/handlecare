package com.example.handlecare.service;

import com.example.handlecare.entity.Deliver;
import com.example.handlecare.entity.enums.Status;

import java.util.List;

public interface DeliverService {
    Deliver save(Deliver deliver);
    Deliver getById(Integer id);
    Deliver findByLogin(String login);
    Deliver findByPhoneNumber(String phoneNumber);
    Deliver findByEmail(String email);
    Deliver updateDeliver(Deliver deliver);
    List<Deliver> findAll();
    List<Deliver> findAllByStatus(Status status);
    List<Deliver> saveAll(List<Deliver> delivers);
    String showName(Integer id);
}
