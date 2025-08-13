package com.example.stemshop.models;

import com.example.stemshop.data.enums.PaymentMethod;
import com.example.stemshop.data.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="payments")
@Getter @Setter
@NoArgsConstructor
public class Payment {
    @Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name="order_id")
    @OneToOne
    private Order order;

    @Column(name="amount")
    private int amount;

    @Column(name="payment_method")
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Column(name="payment_status")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Column(name="transaction_id")
    private String transactionId;

    @Column(name="created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

}
