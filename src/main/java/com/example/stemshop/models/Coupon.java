package com.example.stemshop.models;

import com.example.stemshop.data.enums.DiscountType;
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

    @Column(name="discount_type")
    @Enumerated(EnumType.STRING)
    private DiscountType discountType;

    @Column(name="discount_value")
    private Integer discountValue;

    @Column(name="min_order_amount")
    private Integer minOrderAmount;

    @Column(name="valid_from")
    private LocalDateTime validFrom;

    @Column(name="valid_to")
    private LocalDateTime validTo;

    @Column(name="usage_limit")
    private Integer usageLimit;
}
