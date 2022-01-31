package com.example.handlecare.service;


import com.example.handlecare.entity.Recipient;
import com.example.handlecare.entity.enums.Status;

import java.util.List;

public interface RecipientService {
    Recipient getById(Integer id);
    List<Recipient> findAll();
    Recipient findByLogin(String login);
    Recipient findByPhoneNumber(String phoneNumber);
    Recipient findByEmail(String email);
    Recipient updateRecipient(Recipient recipient);
    List<Recipient> findAllByStatus(Status status);
    Recipient save(Recipient recipient);
    List<Recipient> saveAll(List<Recipient> recipients);

}
