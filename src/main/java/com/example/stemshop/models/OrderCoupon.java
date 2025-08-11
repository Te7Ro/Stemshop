package com.example.stemshop.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="order_coupons")
@Getter @Setter
@RequiredArgsConstructor
public class OrderCoupon {
    @Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name="order_id")
    @ManyToOne
    private Order order;

    @JoinColumn(name="coupon_id")
    @ManyToOne
    private Coupon coupon;
}
