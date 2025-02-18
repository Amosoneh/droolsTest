package com.ecomart.repositories;

import com.ecomart.datas.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    Product findProductById(String productId);

    Product findProductByName(String productName);
}
