package com.ecomart.repositories;

import com.ecomart.datas.models.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    Order findByReferenceCode(String referenceCode);
}
