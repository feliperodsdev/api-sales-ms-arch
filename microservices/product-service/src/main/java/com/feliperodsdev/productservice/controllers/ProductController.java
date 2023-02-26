package com.feliperodsdev.productservice.controllers;

import com.feliperodsdev.productservice.controllers.response.HttpResponse;
import com.feliperodsdev.productservice.dtos.CreateProductDto;
import com.feliperodsdev.productservice.model.Product;
import com.feliperodsdev.productservice.services.CreateProductService;
import com.feliperodsdev.productservice.services.GetAllProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private CreateProductService createProductService;

    @Autowired
    private GetAllProductsService getAllProductsService;

    @PostMapping("/create")
    public ResponseEntity<Object> createProduct(@RequestBody CreateProductDto createProductDto){
        HttpResponse response = new HttpResponse();
        try {
            String[] requiredFields = {"name", "desc", "price"};

            for (String field : requiredFields) {
                try {
                    Field declaredField = createProductDto.getClass().getDeclaredField(field);
                    declaredField.setAccessible(true);
                    if (declaredField.get(createProductDto) == null) {
                        return response.badRequest("Missing Param: " + field);
                    }
                } catch (NoSuchFieldException e) {
                    return response.serverError(e.getMessage());
                }
            }

            createProductService.createProduct(createProductDto);

            return response.created("Created");

        }catch (Exception e){
            return response.serverError(e);
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAllProducts(){
        HttpResponse response = new HttpResponse();
        try {
            List<Product> products = getAllProductsService.getAllProducts();
            return response.ok(products);
        }catch(Exception e){
            return response.serverError(e.getMessage());
        }
    }
}
