package com.example.stemshop.util;

import com.example.stemshop.dto.response.coupon.CouponResponse;
import com.example.stemshop.models.Coupon;
import org.springframework.stereotype.Component;

@Component
public class CouponMapper {
    public CouponResponse toResponse(Coupon coupon) {
        return new CouponResponse(
                coupon.getCode(),
                coupon.getDiscountPercent(),
                coupon.getDiscountAmount(),
                coupon.getValidFrom(),
                coupon.getValidTo()
        );
    }
}
