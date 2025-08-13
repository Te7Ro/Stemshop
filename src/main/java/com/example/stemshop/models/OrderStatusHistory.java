package com.example.stemshop.models;

import com.example.stemshop.data.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="order_status_history")
@Getter @Setter
@NoArgsConstructor
public class OrderStatusHistory {
    @Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name="order_id")
    @ManyToOne
    private Order order;

    @Column(name="old_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus oldStatus;

    @Column(name="new_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus newStatus;

    @Column(name="created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;
}
