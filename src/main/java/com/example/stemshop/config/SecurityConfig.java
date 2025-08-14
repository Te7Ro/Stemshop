package com.example.stemshop.config;

import com.example.stemshop.security.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    private final JwtFilter jwtFilter;
    private final String STORE_ADMIN = "STORE_ADMIN";
    private final String CONTENT_MANAGER = "CONTENT_MANAGER";
    private final String SUPPORT = "SUPPORT";

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(HttpMethod.POST,"/api/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/cart").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/catalog").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/coupon/add").hasAnyRole(STORE_ADMIN,CONTENT_MANAGER)
                        .requestMatchers(HttpMethod.PATCH ,"/api/coupon/*").hasAnyRole(STORE_ADMIN,CONTENT_MANAGER)
                        .requestMatchers(HttpMethod.DELETE ,"/api/coupon/*").hasAnyRole(STORE_ADMIN,CONTENT_MANAGER)
                        .requestMatchers(HttpMethod.GET, "/api/coupon/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/favourites").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/order/*").hasAnyRole(STORE_ADMIN,SUPPORT)
                        .requestMatchers(HttpMethod.POST, "/api/order/add").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/payment/*").hasAnyRole(STORE_ADMIN,SUPPORT)
                        .requestMatchers(HttpMethod.POST, "/api/product/add").hasAnyRole(STORE_ADMIN, CONTENT_MANAGER)
                        .requestMatchers(HttpMethod.PATCH, "/api/product/*").hasAnyRole(STORE_ADMIN, CONTENT_MANAGER)
                        .requestMatchers(HttpMethod.DELETE, "/api/product/*").hasAnyRole(STORE_ADMIN, CONTENT_MANAGER)
                        .requestMatchers(HttpMethod.GET, "/api/product/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/review/add").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/review/**").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
