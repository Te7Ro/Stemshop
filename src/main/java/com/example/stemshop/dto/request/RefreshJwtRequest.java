package com.example.stemshop.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RefreshJwtRequest {
    private String refreshToken;
}
