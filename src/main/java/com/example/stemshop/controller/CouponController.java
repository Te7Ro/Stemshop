package com.example.stemshop.controller;

import com.example.stemshop.dto.request.coupon.CouponAddRequest;
import com.example.stemshop.dto.request.coupon.CouponUpdateRequest;
import com.example.stemshop.dto.response.coupon.CouponResponse;
import com.example.stemshop.services.coupon.CouponService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/coupon")
@RequiredArgsConstructor
@Tag(name = "Купоны для скидов",description = "Получение и управление купонами со скидок(Процентная и фиксированная)")
public class CouponController {
    private final CouponService couponService;

    @Operation(summary = "Получение купона")
    @GetMapping("/{code}")
    public ResponseEntity<CouponResponse> getCoupon(@PathVariable String code) {
        return ResponseEntity.ok(couponService.getCoupon(code));
    }

    @Operation(summary = "Добавление купона, требуется права Админа или Контент менеджера")
    @PostMapping("/add")
    public ResponseEntity<CouponResponse> createCoupon(@Valid @RequestBody CouponAddRequest couponAddRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(couponService.addCoupon(couponAddRequest));
    }

    @Operation(summary = "Обновление купона(Ненужные поля могут быть пропущены), требуется права Админа или Контент менеджера")
    @PatchMapping("/{code}")
    public ResponseEntity<CouponResponse> updateCoupon(
            @PathVariable String code,
            @Valid @RequestBody CouponUpdateRequest couponUpdateRequest
    ) {
        return ResponseEntity.ok(couponService.updateCoupon(code, couponUpdateRequest));
    }

    @Operation(summary = "Удаление купона, требуется права Админа или Контент менеджера")
    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteCoupon(@PathVariable String code) {
        couponService.deleteCoupon(code);
        return ResponseEntity.noContent().build();

    }
}
