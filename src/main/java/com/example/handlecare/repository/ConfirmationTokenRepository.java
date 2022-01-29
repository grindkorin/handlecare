package com.example.handlecare.repository;

import com.example.handlecare.security.token.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Integer> {
    ConfirmationToken findByToken(String token);
    List<ConfirmationToken> findAllByToken(String token);
}
