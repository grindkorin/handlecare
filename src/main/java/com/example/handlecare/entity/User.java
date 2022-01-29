package com.example.handlecare.entity;


import com.example.handlecare.entity.enums.Roles;
import com.example.handlecare.entity.enums.Status;
import com.example.handlecare.security.PasswordConfig;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

import java.util.Set;

import static com.example.handlecare.entity.enums.Status.UNCHECKED;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

//

@MappedSuperclass
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 20, unique = true)
    private String login;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, length = 20, unique = true)
    private String email;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, length = 20)
    private String lastName;
    @Column(nullable = false, length = 20, unique = true)
    private String phoneNumber;
    @Column(length = 20)
    private String district;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Status status;
    @Column(nullable = false, length = 5)
    private Boolean confirmedMail = false;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Roles role;

    public User(Integer id, String login, String password, String email, String name, String lastName,
                String phoneNumber, String district, Status status, Roles role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.district = district;
        this.status = status;
        this.role = role;
    }
}
