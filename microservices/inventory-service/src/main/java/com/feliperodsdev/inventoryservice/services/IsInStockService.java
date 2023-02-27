package com.feliperodsdev.inventoryservice.services;

import com.feliperodsdev.inventoryservice.dtos.InventoryResponse;
import com.feliperodsdev.inventoryservice.model.Inventory;
import com.feliperodsdev.inventoryservice.repositories.IInventoryRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IsInStockService {

    private final IInventoryRepository repository;

    public IsInStockService(@Qualifier("InventoryMySqlRepository") IInventoryRepository repository) {
        this.repository = repository;
    }

    public List<InventoryResponse> isInStock(List<String> skuCode){
        return repository.findBySkuCodeIn(skuCode).stream()
                .map(inventory ->
                    new InventoryResponse(inventory.getSkuCode(), inventory.getQuantity() > 0)
                ).collect(Collectors.toList());
    }

}
