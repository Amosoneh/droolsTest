package com.ecomart.datas.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;

@Setter @Getter @Builder
@AllArgsConstructor @NoArgsConstructor
@Entity
public class CartItem {
    @Id
    @UuidGenerator
    private String id;
    private String productId;
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;
    private int quantity;
    private BigDecimal unitPrice;

}
