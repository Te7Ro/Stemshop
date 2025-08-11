package com.example.stemshop.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="products")
@Getter @Setter
@RequiredArgsConstructor
public class Product {
    @Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="article")
    private String article;

    @Column(name="price")
    private int price;

    @Column(name="photo")
    private String photo;

    @Column(name="desctiption")
    private String description;

    @Column(name="technical_characteristics")
    private String technicalCharacteristics;

    @Column(name="stock")
    private int stock;

    @Column(name="brand")
    private String brand;

    @Column(name="created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
