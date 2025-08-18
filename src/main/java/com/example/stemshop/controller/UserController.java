package com.example.stemshop.controller;

import com.example.stemshop.data.enums.Role;
import com.example.stemshop.dto.request.user.UserAddressUpdateRequest;
import com.example.stemshop.dto.request.user.UserCredentialsUpdateRequest;
import com.example.stemshop.dto.response.user.UserResponse;
import com.example.stemshop.services.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserResponse> getUser() {
        return ResponseEntity.ok(userService.getUserResponse());
    }

    @PatchMapping("/credentials")
    public ResponseEntity<Void> updateUserCredentials(@RequestBody @Valid UserCredentialsUpdateRequest request) {
        userService.updateUserCredentials(request);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/address")
    public ResponseEntity<Void> updateUserAddress(@RequestBody @Valid UserAddressUpdateRequest request) {
        userService.updateUserAddress(request);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> setUserRole(
            @PathVariable Long id,
            @RequestBody Role role
    ){
        userService.setUserRole(id, role);
        return ResponseEntity.ok().build();
    }
}
