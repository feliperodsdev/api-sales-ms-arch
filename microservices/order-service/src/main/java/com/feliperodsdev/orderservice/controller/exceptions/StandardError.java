package com.feliperodsdev.orderservice.controller.exceptions;

import org.springframework.http.HttpStatus;

public class StandardError {

    private HttpStatus statusCode;
    private String message;

    public StandardError(HttpStatus statusCode, String message) {
        super();
        this.statusCode = statusCode;
        this.message = message;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
