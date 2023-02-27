package com.feliperodsdev.orderservice.service.exceptions;

public class OrderWasNotPlaced extends RuntimeException {
    public OrderWasNotPlaced() {
        super("Order cannot be placed, try again later");
    }
}
