package com.example.handlecare.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

import static com.example.handlecare.entity.enums.Roles.RECIPIENT;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

//
@Entity
@Table(name = "recipients")
public class Recipient extends User {
    @Column(length = 20)
    private String address;

    @OneToMany(mappedBy = "recipient",
            cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Order> orders;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipient recipient = (Recipient) o;
        return Objects.equals(address, recipient.address) &&
                Objects.equals(orders, recipient.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, orders);
    }

    public Recipient(User user) {
        this.setLogin(user.getLogin());
        this.setPhoneNumber(user.getPhoneNumber());
        this.setPassword(user.getPassword());
        this.setEmail(user.getEmail());
        this.setName(user.getName());
        this.setLastName(user.getLastName());
        this.setStatus(user.getStatus());
        this.setRole(RECIPIENT);
    }

    public void fromUser(Recipient recipient, User user) {
        if (user.getLogin() != null)
            recipient.setLogin(user.getLogin());
        if (user.getPhoneNumber() != null)
            recipient.setPhoneNumber(user.getPhoneNumber());
        if (user.getPassword() != null)
            recipient.setPassword(user.getPassword());
        if (user.getEmail() != null)
            recipient.setEmail(user.getEmail());
        if (user.getName() != null)
            recipient.setName(user.getName());
        if(user.getLastName() != null)
            recipient.setLastName(user.getLastName());
        if(user.getStatus() != null)
            recipient.setStatus(user.getStatus());
        recipient.setRole(RECIPIENT);
    }


}
