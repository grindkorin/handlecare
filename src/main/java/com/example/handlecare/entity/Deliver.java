package com.example.handlecare.entity;

import com.example.handlecare.entity.enums.Roles;
import com.example.handlecare.entity.enums.Status;
import lombok.*;


import javax.persistence.*;
import java.util.List;
import java.util.Objects;

import static com.example.handlecare.entity.enums.Roles.DELIVER;


@Getter
@Setter
@NoArgsConstructor


//
@Entity
@Table(name = "delivers")
public class Deliver extends User {

    @OneToMany(mappedBy = "deliver",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<Order> orders;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deliver deliver = (Deliver) o;
        return Objects.equals(orders, deliver.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orders);
    }

    public void fromUser(Deliver deliver, User user) {
        if (user.getLogin() != null)
            deliver.setLogin(user.getLogin());
        if (user.getPhoneNumber() != null)
            deliver.setPhoneNumber(user.getPhoneNumber());
        if (user.getPassword() != null)
            deliver.setPassword(user.getPassword());
        if (user.getEmail() != null)
            deliver.setEmail(user.getEmail());
        if (user.getName() != null)
            deliver.setName(user.getName());
        if (user.getLastName() != null)
            deliver.setLastName(user.getLastName());
        if (user.getStatus() != null)
            deliver.setStatus(user.getStatus());
        if (user.getDistrict() != null)
            deliver.setDistrict(user.getDistrict());
        deliver.setRole(DELIVER);
    }


    public Deliver(User user) {
        this.setLogin(user.getLogin());
        this.setPhoneNumber(user.getPhoneNumber());
        this.setPassword(user.getPassword());
        this.setEmail(user.getEmail());
        this.setName(user.getName());
        this.setLastName(user.getLastName());
        this.setStatus(user.getStatus());
        this.setRole(DELIVER);
    }

}

