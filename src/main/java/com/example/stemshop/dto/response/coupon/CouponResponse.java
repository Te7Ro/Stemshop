package com.example.stemshop.dto.response.coupon;

import com.example.stemshop.data.enums.DiscountType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CouponResponse {
    private String code;
    private DiscountType discountType;
    private Integer discountValue;
    private Integer minOrderAmount;
    private LocalDateTime validFrom;
    private LocalDateTime validTo;

    @JsonProperty("minOrderAmount")
    public Integer getMinOrderAmount() {
        if(discountType == DiscountType.FIXED){
            return minOrderAmount;
        }
        return null;
    }
}
