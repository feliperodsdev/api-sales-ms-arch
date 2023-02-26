package com.feliperodsdev.orderservice.controller;

import com.feliperodsdev.orderservice.dtos.DoOrderDto;
import com.feliperodsdev.orderservice.dtos.HttpResponse;
import com.feliperodsdev.orderservice.service.DoOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private DoOrderService doOrderService;

    @PostMapping
    public ResponseEntity<Object> doOrder(@RequestBody DoOrderDto doOrderDto){
        HttpResponse response = new HttpResponse();
        try {
            String[] requiredFields = {"orderItemDtoList"};

            for (String field : requiredFields) {
                try {
                    Field declaredField = doOrderDto.getClass().getDeclaredField(field);
                    declaredField.setAccessible(true);
                    if (declaredField.get(doOrderDto) == null) {
                        return response.badRequest("Missing Param: " + field);
                    }
                } catch (NoSuchFieldException e) {
                    return response.serverError(e.getMessage());
                }
            }
            doOrderService.doOrder(doOrderDto);
            return response.ok("Order was did");
        }catch(Exception e){
            return response.serverError(e.getMessage());
        }
    }

}
