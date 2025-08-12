package com.example.stemshop.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="order_items")
@Getter @Setter
public class OrderItem {
    @Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name="order_id")
    @ManyToOne
    private Order order;

    @Column(name="price")
    private int price;

    @Column(name="quantity")
    private int quantity;
}
