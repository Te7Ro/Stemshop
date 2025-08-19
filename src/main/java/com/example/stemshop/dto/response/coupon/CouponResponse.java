package com.example.stemshop.dto.response.coupon;

import com.example.stemshop.data.enums.DiscountType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CouponResponse {
    @Schema(description = "Код купона", example = "SALE500")
    private String code;

    @Schema(description = "Тип скидки", example = "FIXED")
    private DiscountType discountType;

    @Schema(description = "Количество скидки", example = "800")
    private Integer discountValue;

    @Schema(description = "Минимальная цена заказа", example = "10000")
    private Integer minOrderAmount;

    @Schema(description = "Срок начала действий купона", example = "2025-01-01T00:00:00")
    private LocalDateTime validFrom;

    @Schema(description = "Срок окончания действий купона", example = "2025-12-31T00:00:00")
    private LocalDateTime validTo;

    @JsonProperty("minOrderAmount")
    public Integer getMinOrderAmount() {
        if(discountType == DiscountType.FIXED){
            return minOrderAmount;
        }
        return null;
    }
}
