package com.ecomart.services;

import com.ecomart.exceptions.NotAdultException;
import com.ecomart.exceptions.ProductNotFoundException;
import com.ecomart.exceptions.UserNotFoundException;

public interface CartService {
    String addProductToCart(String productId, String userId, int quantity) throws UserNotFoundException, ProductNotFoundException, NotAdultException;
}
