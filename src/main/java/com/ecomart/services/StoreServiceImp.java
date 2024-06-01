package com.ecomart.services;

import com.ecomart.datas.dtos.request.CreateStoreRequest;
import com.ecomart.datas.models.Store;
import com.ecomart.exceptions.UserNotFoundException;
import com.ecomart.repositories.CustomerRepository;
import com.ecomart.repositories.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreServiceImp implements StoreService {
    private final StoreRepository storeRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private final CustomerRepository customerRepository;
    @Override
    public Store createStore(CreateStoreRequest request) throws UserNotFoundException {
//        var customer = customerRepository.findCustomerById(request.getUserId());
        var anyMatch = customerRepository.existsById(request.getUserId());
        if(!anyMatch){
            throw new UserNotFoundException("User not found");
        }
        var store = modelMapper.map(request, Store.class);
//        store.setUserId(customer.getId());
        return storeRepository.save(store);
    }
}
