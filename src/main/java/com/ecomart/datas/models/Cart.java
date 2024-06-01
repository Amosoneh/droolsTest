package com.ecomart.datas.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Setter @Entity @Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Cart {
    @Id
    @UuidGenerator
    private String id;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<CartItem> cartItems;
    @Builder.Default
    private BigDecimal subTotal = BigDecimal.ZERO;
}
