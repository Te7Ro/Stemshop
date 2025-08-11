package com.example.stemshop.models;

import com.example.stemshop.data.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="users")
@Getter @Setter
@RequiredArgsConstructor
public class User {
    @Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="full_name")
    private String fullName;

    @Column(name="phone")
    private String phone;

    @Column(name="role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name="created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
