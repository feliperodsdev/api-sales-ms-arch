package com.feliperodsdev.inventoryservice.services;

import com.feliperodsdev.inventoryservice.repositories.IInventoryRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class IsInStockService {

    private final IInventoryRepository repository;

    public IsInStockService(@Qualifier("InventoryMySqlRepository") IInventoryRepository repository) {
        this.repository = repository;
    }

    public boolean isInStock(String skuCode){
        return repository.findBySkuCode(skuCode).isPresent();
    }

}
