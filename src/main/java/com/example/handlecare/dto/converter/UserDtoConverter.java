package com.example.handlecare.dto.converter;

import com.example.handlecare.dto.UserDto;
import com.example.handlecare.entity.Deliver;
import com.example.handlecare.entity.Recipient;
import com.example.handlecare.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDtoConverter {

    public UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setLogin(user.getLogin());
        dto.setPassword(user.getPassword());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setName(user.getName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setType(user.getClass().toString());
        dto.setRole(user.getRole());
        return dto;
    }

    public List<UserDto> allToDto(List<User> users) {
        return users.stream().map(this::toDto).collect(Collectors.toList());
    }

    public User fromDto(UserDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setLogin(dto.getLogin());
        user.setPassword(dto.getPassword());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setName(dto.getName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        if (dto.getType().equals("deliver")) {

            return new Deliver(user);
        } else {
            Recipient recipient = new Recipient(user);
            recipient.setAddress(dto.getAddress());
            return recipient;
        }
    }

    public List<User> allFromDto(List<UserDto> dto) {
        return dto.stream().map(this::fromDto).collect(Collectors.toList());
    }
}
