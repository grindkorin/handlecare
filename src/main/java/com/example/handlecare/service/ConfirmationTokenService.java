package com.example.handlecare.service;

import com.example.handlecare.entity.User;
import com.example.handlecare.security.token.ConfirmationToken;

public interface ConfirmationTokenService {
    ConfirmationToken saveConfirmationToken(ConfirmationToken token);

    ConfirmationToken bondNewToken(User user);

    ConfirmationToken findByToken(String token);
}
