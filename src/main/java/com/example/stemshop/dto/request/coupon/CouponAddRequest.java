package com.example.stemshop.dto.request.coupon;

import com.example.stemshop.data.enums.DiscountType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CouponAddRequest {
    @Schema(description = "Код купона", example = "SALE10")
    @NotBlank(message = "Код купона обязателен")
    @Size(min = 3, max = 50, message = "Код купона должен быть от 3 до 50 символов")
    private String code;

    @Schema(description = "Тип скидки", example = "FIXED/PERCENT")
    @NotNull(message = "Тип скидки обязателен")
    private DiscountType discountType;

    @Schema(description = "Количество скидок(процентная или фиксированная)", example = "50")
    @NotNull(message = "Значение купона обязательна")
    @Min(value = 1, message = "Скидка должна быть больше 0")
    private Integer discountValue;

    @Schema(description = "Минимальная сумма заказа для фиксированной скидки(может быть пропущен если тип купона PERCENT)", example = "10000")
    @Min(value = 0, message = "Минимальная сумма заказа не может быть отрицательной")
    private Integer minOrderAmount;

    @Schema(description = "Дата начала действии купона", example = "2025-01-01T00:00:00")
    @NotNull(message = "Дата начала действия обязательна")
    private LocalDateTime validFrom;

    @Schema(description = "Дата окончания действии купона", example = "2025-12-31T00:00:00")
    @NotNull(message = "Дата окончания действия обязательна")
    @Future(message = "Дата окончания должна быть в будущем")
    private LocalDateTime validTo;

    @Schema(description = "Количество использовании", example = "50")
    @NotNull(message = "Количество использовании обязательно")
    @Min(value = 0, message = "Лимит использования не может быть отрицательным")
    private Integer usageLimit;
}
