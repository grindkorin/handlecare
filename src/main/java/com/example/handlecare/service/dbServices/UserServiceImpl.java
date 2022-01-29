package com.example.handlecare.service.dbServices;

import com.example.handlecare.dto.UserDto;
import com.example.handlecare.dto.converter.UserDtoConverter;
import com.example.handlecare.entity.Deliver;
import com.example.handlecare.entity.Recipient;
import com.example.handlecare.entity.User;
import com.example.handlecare.repository.UserRepository;
import com.example.handlecare.security.email.EmailSender;
import com.example.handlecare.security.email.EmailSenderImpl;
import com.example.handlecare.security.token.ConfirmationToken;
import com.example.handlecare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.example.handlecare.entity.enums.Status.*;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    DeliverServiceImpl deliverService;

    @Autowired
    RecipientServiceImpl recipientService;

    @Autowired
    UserDtoConverter converter;

  //  @Autowired
   // RegistrationService registrationService;

    @Autowired
    EmailSenderImpl emailSender;


    @Override
    public User registerNewUserAccount(UserDto userDto) throws Exception {
        String login = userDto.getLogin();
        String email = userDto.getEmail();
        String phoneNumber = userDto.getPhoneNumber();
        if (loginCheck(login) & emailCheck(email) & phoneNumberCheck(phoneNumber)) {
            User user = converter.fromDto(userDto);
            user.setStatus(UNCHECKED);
            //return userRepository.save(user);
            return user;
        }
        return null;
    }

    private boolean loginCheck(String login) throws Exception {
        if ((deliverService.findByLogin(login) != null)) {
            throw new Exception("Курьер с логином\"" + login + "\" уже существует");
        } else if ((recipientService.findByLogin(login) != null)) {
            throw new Exception("Пользователь с логином\"" + login + "\" уже существует");
        }
        return true;
    }

    private boolean emailCheck(String email) throws Exception {
        if ((deliverService.findByEmail(email) != null)) {
            throw new Exception("Курьер с e-mail\"" + email + "\" уже существует");
        } else if ((recipientService.findByEmail(email) != null)) {
            throw new Exception("Пользователь с e-mail\"" + email + "\" уже существует");
        }
        return true;
    }

    private boolean phoneNumberCheck(String phoneNumber) throws Exception {
        if ((deliverService.findByPhoneNumber(phoneNumber) != null)) {
            throw new Exception("Курьер с номером телефона\"" + phoneNumber + "\"уже существует");
        } else if ((recipientService.findByPhoneNumber(phoneNumber) != null)) {
            throw new Exception("Пользователь с номером телефона\"" + phoneNumber + "\"уже существует");
        }
        return true;
    }


    public void setMailConfirmed(String email) {
        Deliver deliver = deliverService.findByEmail(email);
        if (deliver != null) {
            deliver.setConfirmedMail(true);
            deliverService.save(deliver);
        }else{
            Recipient recipient = recipientService.findByEmail(email);
            recipient.setConfirmedMail(true);
            recipientService.save(recipient);
        }
    }



}
