package com.ecomart.services;

import com.ecomart.datas.dtos.request.CreateStoreRequest;
import com.ecomart.datas.models.Store;
import com.ecomart.exceptions.UserNotFoundException;

public interface StoreService {
    Store createStore(CreateStoreRequest request) throws UserNotFoundException;
}
