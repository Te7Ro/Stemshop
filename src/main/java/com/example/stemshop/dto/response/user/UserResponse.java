package com.example.stemshop.dto.response.user;

import lombok.Data;

@Data
public class UserResponse {
    private String email;
    private String fullName;
    private String phone;
    private String address;
    private String city;
    private String postalCode;
    private String country;
}
