package com.example.handlecare.service.dbServices;

import com.example.handlecare.dto.UserDto;
import com.example.handlecare.dto.converter.UserDtoConverter;
import com.example.handlecare.entity.Deliver;
import com.example.handlecare.entity.Recipient;
import com.example.handlecare.entity.User;
import com.example.handlecare.repository.UserRepository;
import com.example.handlecare.security.email.EmailSenderImpl;
import com.example.handlecare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.handlecare.entity.enums.Status.UNCHECKED;


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

    @Autowired
    ConfirmationTokenServiceImpl tokenService;

    @Autowired
    EmailSenderImpl emailSender;

    @Transactional
    @Override
    public User registerNewUserAccount(UserDto userDto) throws Exception {
        String login = userDto.getLogin();
        String email = userDto.getEmail();
        String phoneNumber = userDto.getPhoneNumber();
        if (notConfirmedEmail(email)) {
            deleteNotConfirmedUser(email);
            System.out.println("\n\n\n DELETED \n\n\n");
        }
        if (loginCheck(login) & phoneNumberCheck(phoneNumber) & (emailCheck(email))) {
            System.out.println("\n\n\nCHECK\n\n\n");
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

        public void deleteNotConfirmedUser (String email){
            if (deliverService.findByEmail(email) != null) {
                tokenService.deleteByDeliver(deliverService.findByEmail(email));
                deliverService.deleteDeliverByEmail(email);
            } else if (recipientService.findByEmail(email) != null) {
                tokenService.deleteByRecipient(recipientService.findByEmail(email));
                recipientService.deleteRecipientByEmail(email);
            } else {
                System.out.println("\n\n\nNOT FOUND");
            }
        }

    }
