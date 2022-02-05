package com.example.handlecare;


import com.example.handlecare.repository.ConfirmationTokenRepository;
import com.example.handlecare.security.PasswordConfig;
import com.example.handlecare.service.dbServices.DeliverServiceImpl;
import com.example.handlecare.service.dbServices.RecipientServiceImpl;
import com.example.handlecare.service.dbServices.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    UserServiceImpl userService;
    @Autowired
    ConfirmationTokenRepository repository;
    @Autowired
    PasswordConfig passwordConfig;

    @EventListener(ApplicationReadyEvent.class)
    public void applicationReady() {
//        Deliver deliver = new Deliver(
//                new User(0, "admin", passwordConfig.passwordEncoder().encode("admin"),
//                        "sample@mail.ru", "admin", "admin",
//                        "+112", "this", Status.ACTIVE, Roles.ADMIN)
//        );
//        deliver.setRole(Roles.ADMIN);
//        deliver.setConfirmedMail(true);
//        deliverService.save(deliver);
    }

}
