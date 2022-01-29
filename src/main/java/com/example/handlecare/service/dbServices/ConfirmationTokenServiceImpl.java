package com.example.handlecare.service.dbServices;

import com.example.handlecare.entity.Deliver;
import com.example.handlecare.entity.Recipient;
import com.example.handlecare.entity.User;
import com.example.handlecare.entity.enums.Roles;
import com.example.handlecare.repository.ConfirmationTokenRepository;
import com.example.handlecare.security.token.ConfirmationToken;
import com.example.handlecare.service.ConfirmationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {
    @Autowired
    private ConfirmationTokenRepository repository;

    public ConfirmationToken saveConfirmationToken(ConfirmationToken token) {
        return repository.save(token);
    }

    public ConfirmationToken bondNewToken(User user) {
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setToken(token);
        confirmationToken.setCreatedAt(LocalDateTime.now());
        confirmationToken.setExpiresAt(LocalDateTime.now().plusMinutes(30));
        if (user.getRole().equals(Roles.DELIVER)) {
            confirmationToken.setDeliver(new Deliver(user));
        } else {
            confirmationToken.setRecipient(new Recipient(user));
        }
        return confirmationToken;
    }

    @Override
    public ConfirmationToken findByToken(String token) {
        return repository.findByToken(token);
    }

    public ConfirmationToken saveAndBondToken(User user) {
       return repository.save(bondNewToken(user));
    }


    public ConfirmationToken setConfirmedAt(String token) {
        ConfirmationToken thisToken = repository.findByToken(token);
        thisToken.setConfirmedAt(LocalDateTime.now());
        return repository.save(thisToken);
    }


}
