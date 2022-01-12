package com.example.handlecare.service;

import com.example.handlecare.dto.UserDto;
import com.example.handlecare.entity.User;

public interface UserService {
    public User registerNewUserAccount(UserDto userDto) throws Exception;
}
