package com.example.handlecare.service.dbServices;


import com.example.handlecare.entity.Deliver;
import com.example.handlecare.entity.Recipient;
import com.example.handlecare.entity.enums.Status;
import com.example.handlecare.repository.RecipientRepository;
import com.example.handlecare.security.PasswordConfig;
import com.example.handlecare.service.RecipientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipientServiceImpl implements RecipientService {
    final
    PasswordConfig passwordConfig;
    final
    RecipientRepository repository;

    public RecipientServiceImpl(RecipientRepository repository, PasswordConfig passwordConfig) {
        this.repository = repository;
        this.passwordConfig = passwordConfig;
    }

    @Override
    public Recipient getById(Integer id) {
        return repository.getById(id);
    }

    @Override
    public List<Recipient> findAll() {
        return repository.findAll();
    }

    @Override
    public Recipient findByLogin(String login) {
        return repository.findByLogin(login);
    }

    @Override
    public Recipient findByPhoneNumber(String phoneNumber) {
        return repository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public Recipient findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Recipient updateRecipient(Recipient recipient) {
        Recipient recipient1 = repository.getById(recipient.getId());
        recipient1.fromUser(recipient1, recipient);
        recipient1.setAddress(recipient.getAddress());
        recipient1.setPassword(passwordConfig.passwordEncoder().encode(recipient.getPassword()));
        repository.save(recipient1);
        return recipient1;
    }

    @Override
    public List<Recipient> findAllByStatus(Status status) {
        return repository.findAllByStatus(status);
    }

    @Override
    public Recipient save(Recipient recipient) {
        return repository.save(recipient);
    }

    @Override
    public List<Recipient> saveAll(List<Recipient> recipients) {
        return repository.saveAll(recipients);
    }
}
