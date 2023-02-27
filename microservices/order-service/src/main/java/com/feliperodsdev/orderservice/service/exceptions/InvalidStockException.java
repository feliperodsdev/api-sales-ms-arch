package com.feliperodsdev.orderservice.service.exceptions;

public class InvalidStockException extends RuntimeException {
    public InvalidStockException() {
        super("Some product is out of stock");
    }
}
