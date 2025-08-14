package com.example.stemshop.controller;

import com.example.stemshop.dto.request.coupon.CouponAddRequest;
import com.example.stemshop.dto.request.coupon.CouponUpdateRequest;
import com.example.stemshop.dto.response.coupon.CouponResponse;
import com.example.stemshop.services.coupon.CouponService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/coupon")
@RequiredArgsConstructor
public class CouponController {
    private final CouponService couponService;

    @GetMapping("/{code}")
    public ResponseEntity<CouponResponse> getCoupon(@PathVariable String code) {
        return ResponseEntity.ok(couponService.getCoupon(code));
    }

    @PostMapping("/add")
    public ResponseEntity<CouponResponse> createCoupon(@Valid @RequestBody CouponAddRequest couponAddRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(couponService.addCoupon(couponAddRequest));
    }

    @PatchMapping("/{code}")
    public ResponseEntity<CouponResponse> updateCoupon(
            @PathVariable String code,
            @Valid @RequestBody CouponUpdateRequest couponUpdateRequest
    ) {
        return ResponseEntity.ok(couponService.updateCoupon(code, couponUpdateRequest));
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteCoupon(@PathVariable String code) {
        couponService.deleteCoupon(code);
        return ResponseEntity.noContent().build();

    }
}
