package com.example.handlecare.dto;


import com.example.handlecare.entity.enums.Roles;
import com.example.handlecare.entity.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserDto {

    private Integer id;

    @NotNull
    @NotEmpty(message = "Логин не может быть пустым")
    private String login;

    @NotNull
    @NotEmpty(message = "Пароль не может быть пустым")
    private String password;

    @NotNull
    @NotEmpty //в россии номер телефона мин 10, мах 11
    @Size(min = 10, max = 11, message = "Введите корректный номер телефона")
    private String phoneNumber;

    @NotNull
    @NotEmpty(message = "Имя не может быть пустым")
    private String name;

    @NotNull
    @NotEmpty(message = "Фамилия не может быть пустой")
    private String lastName;

    @NotNull
    @NotEmpty(message = "E-mail не может быть пустым")
    private String email;

    private String type;
    private Status status;
    private String district;
    private String address;
    private Roles role;
}
