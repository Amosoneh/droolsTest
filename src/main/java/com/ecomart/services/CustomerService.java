package com.ecomart.services;

import com.ecomart.datas.dtos.request.CustomerRegisterRequest;
import com.ecomart.datas.models.Customer;
import com.ecomart.exceptions.EmailAlreadyExistException;
import com.ecomart.exceptions.UserNotFoundException;

import java.util.Map;

public interface CustomerService {
    Customer register(CustomerRegisterRequest request) throws EmailAlreadyExistException;
}
