package com.example.stemshop.services.coupon;

import com.example.stemshop.data.enums.DiscountType;
import com.example.stemshop.dto.request.coupon.CouponAddRequest;
import com.example.stemshop.dto.request.coupon.CouponUpdateRequest;
import com.example.stemshop.dto.response.coupon.CouponResponse;
import com.example.stemshop.exceptions.CouponException;
import com.example.stemshop.models.Coupon;
import com.example.stemshop.models.Order;
import com.example.stemshop.models.OrderCoupon;
import com.example.stemshop.repositories.CouponRepository;
import com.example.stemshop.repositories.OrderCouponRepository;
import com.example.stemshop.util.CouponMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponRepository couponRepository;
    private final OrderCouponRepository orderCouponRepository;
    private final CouponMapper couponMapper;

    public CouponResponse getCoupon(String code){
        final Coupon coupon = couponRepository.findByCode(code)
                .orElseThrow(() -> new CouponException("Купон не найден"));
        return couponMapper.toResponse(coupon);
    }

    public CouponResponse addCoupon(CouponAddRequest request) {
        Coupon coupon = new Coupon();
        coupon.setCode(request.getCode());
        coupon.setDiscountType(request.getDiscountType());
        coupon.setDiscountValue(request.getDiscountValue());
        coupon.setMinOrderAmount(request.getMinOrderAmount());
        coupon.setValidFrom(request.getValidFrom());
        coupon.setValidTo(request.getValidTo());
        coupon.setUsageLimit(request.getUsageLimit());
        couponRepository.save(coupon);
        return couponMapper.toResponse(coupon);
    }

    public CouponResponse updateCoupon(String code, CouponUpdateRequest request) {
        final Coupon coupon = couponRepository.findByCode(code)
                .orElseThrow(() -> new CouponException("Купон не найден"));

        if (request.getDiscountType() != null) coupon.setDiscountType(request.getDiscountType());
        if (request.getDiscountValue() != null) coupon.setDiscountValue(request.getDiscountValue());
        if (request.getMinOrderAmount() != null) coupon.setMinOrderAmount(request.getMinOrderAmount());
        if (request.getValidFrom() != null) coupon.setValidFrom(request.getValidFrom());
        if (request.getValidTo() != null) coupon.setValidTo(request.getValidTo());
        if (request.getUsageLimit() != null) coupon.setUsageLimit(request.getUsageLimit());

        couponRepository.save(coupon);
        return couponMapper.toResponse(coupon);
    }

    public void deleteCoupon(String code) {
        final Coupon coupon = couponRepository.findByCode(code)
                .orElseThrow(() -> new CouponException("Купон не найден"));
        couponRepository.delete(coupon);
    }

    @Transactional
    public int applyCoupon(String code, Order order) {
        final Coupon coupon = couponRepository.findByCode(code)
                .orElseThrow(() -> new CouponException("Купон не найден"));

        // Проверки
        if (coupon.getValidFrom().isAfter(LocalDateTime.now())) {
            throw new CouponException("Срок действия купона ещё не начался");
        }
        if (coupon.getValidTo().isBefore(LocalDateTime.now())) {
            throw new CouponException("Срок действия купона истёк");
        }
        if (coupon.getUsageLimit() != null && coupon.getUsageLimit() <= 0) {
            throw new CouponException("Купон больше не доступен");
        }
        if (coupon.getDiscountType() == DiscountType.FIXED &&
                order.getTotalPrice() < coupon.getMinOrderAmount()) {
            throw new CouponException("Минимальная сумма заказа для этого купона: " + coupon.getMinOrderAmount());
        }

        int discount;
        if (coupon.getDiscountType() == DiscountType.PERCENT) {
            discount = order.getTotalPrice() * coupon.getDiscountValue() / 100;
        } else { // FIXED
            discount = coupon.getDiscountValue();
            if (discount > order.getTotalPrice()) {
                discount = order.getTotalPrice(); // чтобы скидка не была больше заказа
            }
        }

        OrderCoupon orderCoupon = new OrderCoupon();
        orderCoupon.setOrder(order);
        orderCoupon.setCoupon(coupon);
        orderCouponRepository.save(orderCoupon);

        if (coupon.getUsageLimit() != null && coupon.getUsageLimit() > 0) {
            coupon.setUsageLimit(coupon.getUsageLimit() - 1);
            couponRepository.save(coupon);
        }

        return discount;

    }
}
