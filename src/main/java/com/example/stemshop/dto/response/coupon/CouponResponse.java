package com.example.stemshop.dto.response.coupon;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CouponResponse {
    private String code;
    private int discountPercent;
    private int discountAmount;
    private LocalDateTime validFrom;
    private LocalDateTime validTo;
}
