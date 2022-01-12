package com.example.handlecare.entity;

import com.example.handlecare.entity.enums.Progression;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

//
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 120)
    private String item;
    @Column(nullable = false, length = 120)
    private String market;
    @Column(nullable = false, length = 120)
    private String address;
    @Column(length = 20)
    private Float amount;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Progression progression;

    @ManyToOne(cascade = {
            CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH
    }, fetch = FetchType.EAGER)
    @JoinTable(name = "orders_reciplient",
            joinColumns = @JoinColumn(
                    name = "order_id", unique = true,
                    nullable = false),
            inverseJoinColumns = @JoinColumn(
                    name = "reciplient_id",
                    nullable = false)
    )
    private Recipient recipient;

    @ManyToOne(cascade = {
            CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH
    }, fetch = FetchType.EAGER)
    @JoinTable(name = "orders_deliver",
            joinColumns = @JoinColumn(
                    name = "order_id", unique = true,
                    nullable = false),
            inverseJoinColumns = @JoinColumn(
                    name = "deliver_id",
                    nullable = false)
    )
    private Deliver deliver;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(item, order.item) &&
                Objects.equals(market, order.market) &&
                Objects.equals(amount, order.amount) &&
                progression == order.progression &&
                Objects.equals(recipient, order.recipient) &&
                Objects.equals(deliver, order.deliver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, item, market, amount, progression, recipient, deliver);
    }
}
