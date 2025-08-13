package com.example.stemshop.dto.request.auth;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RefreshJwtRequest {
    private String refreshToken;
}
