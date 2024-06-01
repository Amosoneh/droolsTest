package com.ecomart.services;

import com.ecomart.datas.dtos.request.CreateProductRequest;
import com.ecomart.datas.models.Product;
import com.ecomart.exceptions.ProductNotFoundException;
import com.ecomart.exceptions.UserNotFoundException;

import java.util.List;

public interface ProductService {
    Product createProduct(CreateProductRequest request) throws UserNotFoundException;
    void deleteProduct(String productId) throws ProductNotFoundException;
    Product getProduct(String productId) throws ProductNotFoundException;
    List<Product> getAllProducts();

}
