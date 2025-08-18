package com.example.stemshop.dto.request.user;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserAddressUpdateRequest {
    @Size(min = 5, max = 255, message = "Адрес должен содержать от 5 до 255 символов")
    private String address;

    @Size(min = 2, max = 100, message = "Город должен содержать от 2 до 100 символов")
    private String city;

    @Pattern(regexp = "^[0-9]{4,10}$", message = "Почтовый индекс должен содержать только цифры (4–10)")
    private String postalCode;

    @Size(min = 2, max = 100, message = "Страна должна содержать от 2 до 100 символов")
    private String country;
}
