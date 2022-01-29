package com.example.handlecare.security.email;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class EmailSenderImpl implements EmailSender {

   private JavaMailSender mailSender;

    @Override
    @Async
    public void send(String to, String email) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,"utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject("Подтверждение адреса почты");
            helper.setFrom("admin@handlecare.ru");
            mailSender.send(mimeMessage);
        }catch (MessagingException e){
            System.err.println(e);
            throw new IllegalStateException("Не удалось отправить сообщение");
        }
    }
}
