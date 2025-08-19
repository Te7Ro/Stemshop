package com.example.stemshop.dto.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserAddressUpdateRequest {
    @Schema(description = "Адрес", example = "улица Пушкина 52")
    @Size(min = 5, max = 255, message = "Адрес должен содержать от 5 до 255 символов")
    private String address;

    @Schema(description = "Город", example = "Алматы")
    @Size(min = 2, max = 100, message = "Город должен содержать от 2 до 100 символов")
    private String city;

    @Schema(description = "Почтовый код", example = "050000")
    @Pattern(regexp = "^[0-9]{4,10}$", message = "Почтовый индекс должен содержать только цифры (4–10)")
    private String postalCode;

    @Schema(description = "Страна", example = "Казахстан")
    @Size(min = 2, max = 100, message = "Страна должна содержать от 2 до 100 символов")
    private String country;
}
