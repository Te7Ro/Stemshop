package com.example.stemshop.dto.request.coupon;

import com.example.stemshop.data.enums.DiscountType;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CouponUpdateRequest {
    private DiscountType discountType;

    @Min(value = 1, message = "Скидка должна быть больше 0")
    private Integer discountValue;

    @Min(value = 0, message = "Минимальная сумма заказа не может быть отрицательной")
    private Integer minOrderAmount;

    private LocalDateTime validFrom;

    @Future(message = "Дата окончания должна быть в будущем")
    private LocalDateTime validTo;

    @Min(value = 0, message = "Лимит использования не может быть отрицательным")
    private Integer usageLimit;
}
