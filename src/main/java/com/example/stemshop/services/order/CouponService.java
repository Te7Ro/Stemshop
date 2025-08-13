package com.example.stemshop.services.order;

import com.example.stemshop.dto.request.coupon.CouponAddRequest;
import com.example.stemshop.dto.request.coupon.CouponUpdateRequest;
import com.example.stemshop.dto.response.coupon.CouponResponse;
import com.example.stemshop.exceptions.CouponException;
import com.example.stemshop.models.Coupon;
import com.example.stemshop.repositories.CouponRepository;
import com.example.stemshop.util.CouponMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponRepository couponRepository;
    private final CouponMapper couponMapper;

    public CouponResponse getCoupon(String code){
        final Coupon coupon = couponRepository.findByCode(code)
                .orElseThrow(() -> new CouponException("Купон не найден"));
        return couponMapper.toResponse(coupon);
    }

    public CouponResponse addCoupon(CouponAddRequest request) {
        Coupon coupon = new Coupon();
        coupon.setCode(request.getCode());
        coupon.setDiscountPercent(request.getDiscountPercent());
        coupon.setDiscountAmount(request.getDiscountAmount());
        coupon.setValidFrom(request.getValidFrom());
        coupon.setValidTo(request.getValidTo());
        couponRepository.save(coupon);
        return couponMapper.toResponse(coupon);
    }

    public CouponResponse updateCoupon(String code, CouponUpdateRequest request) {
        final Coupon coupon = couponRepository.findByCode(code)
                .orElseThrow(() -> new CouponException("Купон не найден"));
        if(request.getDiscountPercent() != null) coupon.setDiscountPercent(request.getDiscountPercent());
        if(request.getDiscountAmount() != null) coupon.setDiscountAmount(request.getDiscountAmount());
        if(request.getValidFrom() != null) coupon.setValidFrom(request.getValidFrom());
        if(request.getValidTo() != null) coupon.setValidTo(request.getValidTo());
        couponRepository.save(coupon);
        return couponMapper.toResponse(coupon);
    }

    public String deleteCoupon(String code) {
        final Coupon coupon = couponRepository.findByCode(code)
                .orElseThrow(() -> new CouponException("Купон не найден"));
        couponRepository.delete(coupon);
        return "Купон успешно удален";
    }
}
