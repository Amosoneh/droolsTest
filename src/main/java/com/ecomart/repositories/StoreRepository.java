package com.ecomart.repositories;

import com.ecomart.datas.models.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, String> {
    Store findStoreByUserId(String userId);
}
