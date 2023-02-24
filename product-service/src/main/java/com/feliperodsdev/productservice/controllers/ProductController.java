package com.feliperodsdev.productservice.controllers;

import com.feliperodsdev.productservice.controllers.response.HttpResponse;
import com.feliperodsdev.productservice.dtos.CreateProductDto;
import com.feliperodsdev.productservice.services.CreateProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;

@RestController
@RequestMapping("/product")
public class ProductController {

    private CreateProductService createProductService;

    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody CreateProductDto createProductDto){
        HttpResponse response = new HttpResponse();
        try {
            String[] requiredFields = {"name", "dateOfBirth"};

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
            return response.serverError(e.getMessage());
        }
    }


}
