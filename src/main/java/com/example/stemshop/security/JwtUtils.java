package com.example.stemshop.security;

import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JwtUtils {

    public static JwtAuthentication generate(Claims claims) {
        JwtAuthentication jwtInfoToken = new JwtAuthentication();
        jwtInfoToken.setUserId(claims.get("userId", Long.class));
        jwtInfoToken.setEmail(claims.get("email", String.class));
        jwtInfoToken.setUsername(claims.getSubject()); // subject = username/email
        String role = claims.get("role", String.class);
        jwtInfoToken.setAuthorities(
                List.of(new SimpleGrantedAuthority("ROLE_" + role)) // для hasRole
        );
        return jwtInfoToken;
    }
}
