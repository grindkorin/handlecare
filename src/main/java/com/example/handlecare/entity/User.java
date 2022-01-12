package com.example.handlecare.entity;


import com.example.handlecare.entity.enums.Roles;
import com.example.handlecare.entity.enums.Status;
import lombok.*;

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
    @Column(nullable = false, length = 20)
    private String password;
    @Column(nullable = false, length = 20, unique = true)
    private String email;
    @Column(nullable = false, length = 20)
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
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Roles role;


    public User clone(User user) {
        return new User(user.getId(), user.getLogin(), user.getPassword(), user.getEmail(), user.getName(), user.getLastName(),
                user.getPhoneNumber(), user.getDistrict(), user.getStatus(), user.getRole());
    }

}
