package com.example.stemshop.dto.request.coupon;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CouponUpdateRequest {

    @Min(value = 0, message = "Скидка в процентах не может быть меньше 0%")
    @Max(value = 100, message = "Скидка в процентах не может быть больше 100%")
    private Integer discountPercent;

    @PositiveOrZero(message = "Количество купонов должна быть 0 или больше")
    private Integer discountAmount;

    private LocalDateTime validFrom;

    @Future(message = "Дата окончания должна быть в будущем")
    private LocalDateTime validTo;
}
