package com.feliperodsdev.inventoryservice.controllers;

import com.feliperodsdev.inventoryservice.dtos.HttpResponse;
import com.feliperodsdev.inventoryservice.services.IsInStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private IsInStockService isInStockService;

    @GetMapping("/{sku_code}")
    public ResponseEntity<Object> isInStock(@PathVariable("sku_code") String skuCode){
        HttpResponse response = new HttpResponse();
        try {
            return response.ok(isInStockService.isInStock(skuCode));
        }catch(Exception e){
            return response.serverError(e.getMessage());
        }
    }

}
