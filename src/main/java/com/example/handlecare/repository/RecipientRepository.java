package com.example.handlecare.repository;

import com.example.handlecare.entity.Recipient;
import com.example.handlecare.entity.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipientRepository extends JpaRepository<Recipient, Integer> {
    Recipient findByLogin(String login);
    Recipient findByEmail(String email);
    Recipient findByPhoneNumber(String phoneNumber);
    List<Recipient> findAllByStatus(Status status);
}
