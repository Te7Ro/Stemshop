package com.example.stemshop.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name="coupons")
@Getter @Setter
@RequiredArgsConstructor
public class Coupon {
    @Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="code")
    private String code;

    @Column(name="discount_percent")
    private int discountPercent;

    @Column(name="discount_amount")
    private int discountAmount;

    @Column(name="valid_from")
    private LocalDateTime validFrom;

    @Column(name="valid_to")
    private LocalDateTime validTo;
}
