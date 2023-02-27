package com.feliperodsdev.inventoryservice.repositories;

import com.feliperodsdev.inventoryservice.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("InventoryMySqlRepository")
public class InventoryRepositoryImpl implements IInventoryRepository {

    @Autowired
    InventoryMySqlRepository inventoryMySqlRepository;


    @Override
    public List<Inventory> findBySkuCodeIn(List<String> skuCode) {
        return inventoryMySqlRepository.findBySkuCodeIn(skuCode);
    }

    @Override
    public void save(Inventory inventory) {
        inventoryMySqlRepository.save(inventory);
    }
}
