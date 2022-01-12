package com.example.handlecare.service.dbServices;

import com.example.handlecare.entity.Deliver;
import com.example.handlecare.entity.User;
import com.example.handlecare.entity.enums.Status;
import com.example.handlecare.repository.DeliverRepository;
import com.example.handlecare.service.DeliverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DeliverServiceImpl implements DeliverService {

    final
    DeliverRepository repository;

    public DeliverServiceImpl(DeliverRepository repository) {
        this.repository = repository;
    }


    @Override
    public Deliver save(Deliver deliver) {
        return repository.save(deliver);
    }

    @Override
    public Deliver getById(Integer id) {
        return repository.getById(id);
    }

    @Override
    public Deliver findByLogin(String login) {
        return repository.findByLogin(login);
    }

    @Override
    public Deliver findByPhoneNumber(String phoneNumber) {
        return repository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public Deliver findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Deliver updateDeliver(Deliver deliver) {
        Deliver deliver1 = repository.getById(deliver.getId());
        deliver1.fromUser(deliver1, deliver);
        repository.save(deliver1);
        return deliver1;
    }

    @Override
    public List<Deliver> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Deliver> findAllByStatus(Status status) {
        return repository.findAllByStatus(status);
    }

    @Override
    public List<Deliver> saveAll(List<Deliver> delivers) {
        return null;
    }

    @Override
    public String showName(Integer id) {
        Deliver deliver = getById(id);
        return deliver.getName();
    }
}