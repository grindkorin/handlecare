package com.example.handlecare.service.dbServices;

import com.example.handlecare.dto.UserDto;
import com.example.handlecare.dto.converter.UserDtoConverter;
import com.example.handlecare.entity.Deliver;
import com.example.handlecare.entity.Recipient;
import com.example.handlecare.entity.User;
import com.example.handlecare.repository.UserRepository;
import com.example.handlecare.security.email.EmailSenderImpl;
import com.example.handlecare.security.token.ConfirmationToken;
import com.example.handlecare.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.handlecare.entity.enums.Status.UNCHECKED;


@Service
public class UserServiceImpl implements UserService {

    final
    UserRepository userRepository;
    final
    DeliverServiceImpl deliverService;
    final
    RecipientServiceImpl recipientService;
    final
    UserDtoConverter converter;
    final
    ConfirmationTokenServiceImpl tokenService;
    final
    EmailSenderImpl emailSender;

    public UserServiceImpl(UserRepository userRepository, DeliverServiceImpl deliverService, RecipientServiceImpl recipientService, UserDtoConverter converter, ConfirmationTokenServiceImpl tokenService, EmailSenderImpl emailSender) {
        this.userRepository = userRepository;
        this.deliverService = deliverService;
        this.recipientService = recipientService;
        this.converter = converter;
        this.tokenService = tokenService;
        this.emailSender = emailSender;
    }

    @Transactional
    @Override
    public User registerNewUserAccount(UserDto userDto) throws Exception {
        String login = userDto.getLogin();
        String email = userDto.getEmail();
        String phoneNumber = userDto.getPhoneNumber();
        if (notConfirmedEmail(email)) {
            deleteNotConfirmedUser(email);
        }
        if (loginCheck(login) & phoneNumberCheck(phoneNumber) & (emailCheck(email))) {
            User user = converter.fromDto(userDto);
            user.setStatus(UNCHECKED);
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
        } else {
            Recipient recipient = recipientService.findByEmail(email);
            recipient.setConfirmedMail(true);
            recipientService.save(recipient);
        }
    }

    public boolean notConfirmedEmail(String email) {
        if (deliverService.findByEmail(email) != null) {
            return !deliverService.findByEmail(email).getConfirmedMail();
        } else if (recipientService.findByEmail(email) != null) {
            return !recipientService.findByEmail(email).getConfirmedMail();
        }
        return false;
    }

    public void deleteNotConfirmedUser(String email) {
        if (deliverService.findByEmail(email) != null) {
            tokenService.deleteByDeliver(deliverService.findByEmail(email));
            deliverService.deleteDeliverByEmail(email);
        } else if (recipientService.findByEmail(email) != null) {
            tokenService.deleteByRecipient(recipientService.findByEmail(email));
            recipientService.deleteRecipientByEmail(email);
        } else {
            System.out.println("NOT FOUND");
        }
    }

    public User findByEmail(String email) {
        if (deliverService.findByEmail(email) != null) {
            return deliverService.findByEmail(email);
        } else if (recipientService.findByEmail(email) != null) {
            return recipientService.findByEmail(email);
        }
        throw new IllegalStateException("Пользователь не найден");
    }

    public User getByToken(String token) {
        ConfirmationToken confirmationToken = tokenService.findByToken(token);
        if (confirmationToken.getDeliver() != null) {
            return confirmationToken.getDeliver();
        } else if (confirmationToken.getRecipient() != null) {
            return confirmationToken.getRecipient();
        }
        throw new IllegalStateException("Пользователь не найден");
    }
}
