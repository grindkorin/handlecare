package com.example.handlecare.service.dbServices;

import com.example.handlecare.dto.UserDto;
import com.example.handlecare.dto.converter.UserDtoConverter;
import com.example.handlecare.entity.User;
import com.example.handlecare.repository.UserRepository;
import com.example.handlecare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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



    @Override
    public User registerNewUserAccount(UserDto userDto) throws Exception {
        String login = userDto.getLogin();
        String email = userDto.getEmail();
        String phoneNumber = userDto.getPhoneNumber();
        if (loginCheck(login) & emailCheck(email) & phoneNumberCheck(phoneNumber)) {
            User user = converter.fromDto(userDto);
            user.setStatus(UNCHECKED);
            return userRepository.save(user);
        }
        return null;
    }

    private boolean loginCheck(String login) throws Exception {
        if ((deliverService.findByLogin (login) != null)) {
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



}
