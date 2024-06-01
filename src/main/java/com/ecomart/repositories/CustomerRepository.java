package com.ecomart.repositories;


import com.ecomart.datas.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    Customer findCustomerByEmail(String email);

    Customer findCustomerById(String userId);
}

