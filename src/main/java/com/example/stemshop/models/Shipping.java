package com.example.stemshop.models;

import com.example.stemshop.data.ShippingMethod;
import com.example.stemshop.data.ShippingStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="shipping")
@Getter @Setter
@NoArgsConstructor
public class Shipping {
    @Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name="order_id")
    @OneToOne
    private Order order;

    @Column(name="address")
    private String address;

    @Column(name="city")
    private String city;

    @Column(name="postal_code")
    private String postalCode;

    @Column(name="country")
    private String country;

    @Column(name="shipping_method")
    @Enumerated(EnumType.STRING)
    private ShippingMethod shippingMethod;

    @Column(name="tracking_number")
    private String trackingNumber;

    @Column(name="shipping_status")
    @Enumerated(EnumType.STRING)
    private ShippingStatus shippingStatus;

    @Column(name="shipped_at")
    private LocalDateTime shippedAt;

    @Column(name="delivered_at")
    private LocalDateTime deliveredAt;
}
