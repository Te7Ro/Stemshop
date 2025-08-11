package com.example.stemshop.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="reviews")
@Getter @Setter
@RequiredArgsConstructor
public class Review {
    @Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name="product_id")
    @ManyToOne
    private Product product;

    @JoinColumn(name="user")
    @ManyToOne
    private User user;

    @Column(name="rating")
    private int rating;

    @Column(name="comment")
    private String comment;

    @Column(name="created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;
}
