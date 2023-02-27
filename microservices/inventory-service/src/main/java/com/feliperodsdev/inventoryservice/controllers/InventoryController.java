package com.feliperodsdev.inventoryservice.controllers;

import com.feliperodsdev.inventoryservice.dtos.HttpResponse;
import com.feliperodsdev.inventoryservice.services.IsInStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private IsInStockService isInStockService;

    @GetMapping
    public ResponseEntity<Object> isInStock(@RequestParam List<String> skuCode){
        HttpResponse response = new HttpResponse();
        try {
            return response.ok(isInStockService.isInStock(skuCode));
        }catch(Exception e){
            return response.serverError(e.getMessage());
        }
    }

}
