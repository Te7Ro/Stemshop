package com.example.stemshop.dto.request.coupon;

import com.example.stemshop.data.enums.DiscountType;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CouponAddRequest {
    @NotBlank(message = "Код купона обязателен")
    @Size(min = 3, max = 50, message = "Код купона должен быть от 3 до 50 символов")
    private String code;

    @NotNull(message = "Тип скидки обязателен")
    private DiscountType discountType;

    @NotNull(message = "Значение купона обязательна")
    @Min(value = 1, message = "Скидка должна быть больше 0")
    private Integer discountValue;

    @Min(value = 0, message = "Минимальная сумма заказа не может быть отрицательной")
    private Integer minOrderAmount;

    @NotNull(message = "Дата начала действия обязательна")
    private LocalDateTime validFrom;

    @NotNull(message = "Дата окончания действия обязательна")
    @Future(message = "Дата окончания должна быть в будущем")
    private LocalDateTime validTo;

    @NotNull(message = "Количество использовании обязательно")
    @Min(value = 0, message = "Лимит использования не может быть отрицательным")
    private Integer usageLimit;
}
