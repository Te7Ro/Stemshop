package com.example.stemshop.controller;

import com.example.stemshop.data.enums.Role;
import com.example.stemshop.dto.request.user.UserAddressUpdateRequest;
import com.example.stemshop.dto.request.user.UserCredentialsUpdateRequest;
import com.example.stemshop.dto.response.user.UserResponse;
import com.example.stemshop.services.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Tag(name = "Пользователи", description = "Управление аккаунтами")
public class UserController {
    private final UserService userService;

    @Operation(summary = "Получение всех данных пользователя. Личный кабинет")
    @GetMapping
    public ResponseEntity<UserResponse> getUser() {
        return ResponseEntity.ok(userService.getUserResponse());
    }

    @Operation(summary = "Обновление данных для входа(Ненужные поля могут быть пропущены)")
    @PatchMapping("/credentials")
    public ResponseEntity<Void> updateUserCredentials(@RequestBody @Valid UserCredentialsUpdateRequest request) {
        userService.updateUserCredentials(request);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Обновление адреса(Ненужные поля могут быть пропущены)")
    @PatchMapping("/address")
    public ResponseEntity<Void> updateUserAddress(@RequestBody @Valid UserAddressUpdateRequest request) {
        userService.updateUserAddress(request);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Обновление роли аккаунта, требуется права Админа")
    @PatchMapping("/{id}")
    public ResponseEntity<Void> setUserRole(
            @PathVariable Long id,
            @RequestBody Role role
    ){
        userService.setUserRole(id, role);
        return ResponseEntity.ok().build();
    }
}
