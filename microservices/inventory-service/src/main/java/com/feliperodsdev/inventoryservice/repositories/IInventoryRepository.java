package com.feliperodsdev.inventoryservice.repositories;

import com.feliperodsdev.inventoryservice.model.Inventory;

import java.util.Optional;

public interface IInventoryRepository {

    Optional<Inventory> findBySkuCode(String skuCode);

    void save(Inventory inventory);

}
