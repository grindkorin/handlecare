package com.example.handlecare.service;

import com.example.handlecare.entity.Deliver;
import com.example.handlecare.entity.Recipient;
import com.example.handlecare.entity.User;
import com.example.handlecare.security.token.ConfirmationToken;

public interface ConfirmationTokenService {
    ConfirmationToken saveConfirmationToken(ConfirmationToken token);
    ConfirmationToken bondNewToken(User user);
    ConfirmationToken findByToken(String token);
    ConfirmationToken findByRecipient(Recipient recipient);
    ConfirmationToken findByDeliver(Deliver deliver);
    Integer deleteByRecipient(Recipient recipient);
    Integer deleteByDeliver(Deliver deliver);

}
