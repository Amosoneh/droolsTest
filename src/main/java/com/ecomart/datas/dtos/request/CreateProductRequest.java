package com.ecomart.datas.dtos.request;

import lombok.*;

@Setter @Getter @Builder
@AllArgsConstructor @NoArgsConstructor
public class CreateProductRequest {
    private String userId;
    private String name;
    private String description;
    private Long price;
    private int quantity;
    private String category;
}
