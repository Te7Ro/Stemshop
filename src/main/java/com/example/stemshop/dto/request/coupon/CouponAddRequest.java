package com.example.stemshop.dto.request.coupon;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CouponAddRequest {
    @NotBlank(message = "Код купона обязателен")
    @Size(min = 3, max = 50, message = "Код купона должен быть от 3 до 50 символов")
    private String code;

    @Min(value = 0, message = "Скидка в процентах не может быть меньше 0%")
    @Max(value = 100, message = "Скидка в процентах не может быть больше 100%")
    private int discountPercent;

    @PositiveOrZero(message = "Количество купонов должна быть 0 или больше")
    private int discountAmount;

    @NotNull(message = "Дата начала действия обязательна")
    private LocalDateTime validFrom;

    @NotNull(message = "Дата окончания действия обязательна")
    @Future(message = "Дата окончания должна быть в будущем")
    private LocalDateTime validTo;
}
