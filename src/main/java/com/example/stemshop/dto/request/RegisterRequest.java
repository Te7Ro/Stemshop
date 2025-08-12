package com.example.stemshop.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message = "Почта не может быть пустым")
    @Email(message = "Неправильный формат почты")
    private String email;

    @NotBlank(message = "Пароль не может быть пустым")
    @Size(min = 8, max = 64, message = "Пароль должен быть не меньше 8 и не больше 64 символов")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
            message = "Пароль должен содержать одну заглавную букву, одну маленькую букву, одну цифру и один символ"
    )
    private String password;

    @NotBlank(message = "ФИО не может быть пустым")
    @Size(min = 2, max = 100, message = "ФИО должен быть не меньше 2 и не больше 100 символов")
    private String fullName;

    @NotBlank(message = "Номер не может быть пустым")
    // пример: +7 (777) 123-45-67 или 87771234567
    @Pattern(
            regexp = "^(\\+?[0-9]{1,3})?\\s?\\(?[0-9]{3}\\)?[\\s-]?[0-9]{3}[\\s-]?[0-9]{2}[\\s-]?[0-9]{2}$",
            message = "Неправильный формат номера"
    )
    private String phone;
}
