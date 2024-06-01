package com.ecomart.datas.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;

@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
@Entity @Builder
public class Product {
    @Id
    @UuidGenerator
    private String id;

    private String name;

    private String description;

    private String category;
    @Builder.Default
    private BigDecimal price = BigDecimal.ZERO;

    private int quantity;

    private boolean isAdultOnly;

}
