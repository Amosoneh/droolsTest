package com.ecomart.services;


import com.ecomart.datas.dtos.response.OrderResponse;
import com.ecomart.exceptions.UserNotFoundException;

public interface OrderService {
    OrderResponse makeOrder(String customerId) throws UserNotFoundException;
}
