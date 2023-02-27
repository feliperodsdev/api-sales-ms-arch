package com.feliperodsdev.inventoryservice.repositories;

import com.feliperodsdev.inventoryservice.model.Inventory;

import java.util.List;
import java.util.Optional;

public interface IInventoryRepository {

    List<Inventory> findBySkuCodeIn(List<String> skuCode);

    void save(Inventory inventory);

}
