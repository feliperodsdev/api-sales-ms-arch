package com.feliperodsdev.orderservice.controller.exceptions;


import com.feliperodsdev.orderservice.dtos.HttpResponse;
import com.feliperodsdev.orderservice.service.exceptions.InvalidStockException;
import com.feliperodsdev.orderservice.service.exceptions.OrderWasNotPlaced;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(InvalidStockException.class)
    public ResponseEntity<Object> invalidStock(InvalidStockException e){
        HttpResponse response = new HttpResponse();
        String error = e.getMessage();
        HttpStatus statusCode = HttpStatus.BAD_REQUEST;
        StandardError standardError = new StandardError(statusCode, error);
        return response.badRequest(standardError);
    }
    @ExceptionHandler(OrderWasNotPlaced.class)
    public ResponseEntity<Object> invalidStock(OrderWasNotPlaced e){
        HttpResponse response = new HttpResponse();
        String error = e.getMessage();
        HttpStatus statusCode = HttpStatus.BAD_REQUEST;
        StandardError standardError = new StandardError(statusCode, error);
        return response.badRequest(standardError);
    }
}
