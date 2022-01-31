package com.example.handlecare.security.token;

import com.example.handlecare.entity.Deliver;
import com.example.handlecare.entity.Recipient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor


@Entity
@Table(name = "confirmation_token")
public class ConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String token;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime expiresAt;
    @Column()
    private LocalDateTime confirmedAt;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "deliver_id")
    private Deliver deliver;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "recipient_id")
    private Recipient recipient;

    public ConfirmationToken(String token,
                             LocalDateTime createdAt,
                             LocalDateTime expiresAt,
                             Deliver deliver) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
           this.deliver = deliver;
    }

    public ConfirmationToken(String token,
                             LocalDateTime createdAt,
                             LocalDateTime expiresAt,
                             Recipient recipient) {

        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.recipient = recipient;
    }

}
