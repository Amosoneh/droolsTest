package com.ecomart.services;

import com.ecomart.datas.dtos.request.CustomerRegisterRequest;
import com.ecomart.datas.models.Cart;
import com.ecomart.datas.models.Customer;
import com.ecomart.datas.models.Role;
import com.ecomart.exceptions.EmailAlreadyExistException;
import com.ecomart.exceptions.UserNotFoundException;
import com.ecomart.repositories.CartRepository;
import com.ecomart.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service @AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper mapper = new ModelMapper();

    private final CartRepository cartRepository;

    @Override
    public Customer register(CustomerRegisterRequest request) throws EmailAlreadyExistException {
        boolean anyMatch = customerRepository.findAll().stream().anyMatch(user -> user.getEmail().equals(request.getEmail()));
        if(anyMatch){
            throw new EmailAlreadyExistException("User already exists");
        }

        Customer customer = mapper.map(request, Customer.class);
        customer.setRole(Role.valueOf(request.getAccountType().toUpperCase()));
        customer.setPassword(request.getPassword());
        customer.setCart(cartRepository.save(new Cart()));
        if(request.getAccountType() != null && request.getAccountType().equalsIgnoreCase(Role.SELLER.name())){
            customer.setRole(Role.SELLER);
        }else customer.setRole(Role.BUYER);
        customer = customerRepository.save(customer);
        return customer;
    }


    public Customer getCustomerById(String id) throws UserNotFoundException {
        var customer =  customerRepository.findCustomerById(id);
        if(customer == null){
            throw new UserNotFoundException("User not found");
        }
        return customer;
    }
}
