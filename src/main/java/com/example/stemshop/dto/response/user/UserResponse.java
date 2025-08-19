package com.example.stemshop.dto.response.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserResponse {

    @Schema(description = "Email", example = "example@site.com")
    private String email;

    @Schema(description = "ФИО пользователя", example = "Имя Фамилия")
    private String fullName;

    @Schema(description = "Номер телефона", example = "87771234567")
    private String phone;

    @Schema(description = "Адрес", example = "Улица Пушкина 52")
    private String address;

    @Schema(description = "Город", example = "Алматы")
    private String city;

    @Schema(description = "Почтовый код", example = "050000")
    private String postalCode;

    @Schema(description = "Страна", example = "Казахстан")
    private String country;
}
