package com.example.stemshop.dto.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserCredentialsUpdateRequest {
    @Schema(description = "Старый пароль", example = "Qwerty1!")
    @Size(min = 8, max = 64, message = "Пароль должен быть не меньше 8 и не больше 64 символов")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
            message = "Пароль должен содержать одну заглавную букву, одну маленькую букву, одну цифру и один символ"
    )
    private String oldPassword;

    @Schema(description = "Новый пароль", example = "Qwerty1!")
    @Size(min = 8, max = 64, message = "Пароль должен быть не меньше 8 и не больше 64 символов")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
            message = "Пароль должен содержать одну заглавную букву, одну маленькую букву, одну цифру и один символ"
    )
    private String newPassword;

    @Schema(description = "Номер телефона", example = "87771234567")
    // пример: +7 (777) 123-45-67 или 87771234567
    @Pattern(
            regexp = "^(\\+?[0-9]{1,3})?\\s?\\(?[0-9]{3}\\)?[\\s-]?[0-9]{3}[\\s-]?[0-9]{2}[\\s-]?[0-9]{2}$",
            message = "Неправильный формат номера"
    )
    private String phone;
}
