package com.ecomart.datas.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Setter @Getter @Builder @AllArgsConstructor
@NoArgsConstructor @Entity
@Table(name = "orders")
public class Order {
    @Id
    @UuidGenerator
    private String id;
    private String orderedBy;
    private BigDecimal totalAmount;
    private String cartId;
    private LocalDateTime orderedAt;
    private String status;
    private String referenceCode;
    private int discount;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private List<CartItem> orderItems;
}
