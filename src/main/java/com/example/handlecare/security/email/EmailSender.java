package com.example.handlecare.security.email;

public interface EmailSender {
    void send(String to, String subject, String email);
}
