package com.ecomart.datas.dtos.response;

import com.ecomart.datas.models.CartItem;
import com.ecomart.datas.models.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder @AllArgsConstructor  @NoArgsConstructor
public class OrderResponse {
    private Order order;
    private String message;


}
