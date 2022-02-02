package com.example.handlecare.service.dbServices;

import com.example.handlecare.entity.Deliver;
import com.example.handlecare.entity.enums.Status;
import com.example.handlecare.repository.DeliverRepository;
import com.example.handlecare.security.PasswordConfig;
import com.example.handlecare.service.DeliverService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DeliverServiceImpl implements DeliverService {

    final
    DeliverRepository repository;
    private final PasswordConfig passwordConfig;

    public DeliverServiceImpl(DeliverRepository repository, PasswordConfig passwordConfig) {
        this.repository = repository;
        this.passwordConfig = passwordConfig;
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
        deliver1.setPassword(passwordConfig.passwordEncoder().
                encode(deliver.getPassword()));
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
    public Integer deleteDeliverByEmail(String email) {
        return repository.deleteDeliverByEmail(email);
    }

    @Override
    public List<Deliver> saveAll(List<Deliver> delivers) {
        return repository.saveAll(delivers);
    }

    @Override
    public String showName(Integer id) {
        return repository.getById(id).getName();
    }


}
