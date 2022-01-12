package com.example.handlecare;

import com.example.handlecare.entity.enums.Status;
import com.example.handlecare.service.RecipientService;
import com.example.handlecare.service.dbServices.DeliverServiceImpl;
import com.example.handlecare.service.dbServices.RecipientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class HandleCareApplication {

    public static void main(String[] args) {
        SpringApplication.run(HandleCareApplication.class, args);
    }

    @Autowired
    DeliverServiceImpl deliverService;
    @Autowired
    RecipientServiceImpl recipientService;

    @EventListener(ApplicationReadyEvent.class)
    public void applicationReady() {
        System.out.println(recipientService.findAllByStatus(Status.UNCHECKED));
        System.out.println(deliverService.findAllByStatus(Status.UNCHECKED));
    }

}
