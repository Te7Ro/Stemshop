package com.example.stemshop.util;

import com.example.stemshop.dto.response.coupon.CouponResponse;
import com.example.stemshop.models.Coupon;
import org.springframework.stereotype.Component;

@Component
public class CouponMapper {
    public CouponResponse toResponse(Coupon coupon) {
        return new CouponResponse(
                coupon.getCode(),
                coupon.getDiscountType(),
                coupon.getDiscountValue(),
                coupon.getMinOrderAmount(),
                coupon.getValidFrom(),
                coupon.getValidTo()
        );
    }
}
