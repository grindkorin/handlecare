package com.example.handlecare.repository;

import com.example.handlecare.entity.Deliver;
import com.example.handlecare.entity.Recipient;
import com.example.handlecare.security.token.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Integer> {
    ConfirmationToken findByToken(String token);
    ConfirmationToken findByRecipient(Recipient recipient);
    ConfirmationToken findByDeliver(Deliver deliver);
    List<ConfirmationToken> findAllByToken(String token);
    Integer deleteByRecipient(Recipient recipient);
    Integer deleteByDeliver(Deliver deliver);
}
