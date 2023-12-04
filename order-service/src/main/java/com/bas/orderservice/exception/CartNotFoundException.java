package com.bas.orderservice.exception;

public class CartNotFoundException extends RuntimeException{
    public CartNotFoundException() {
        super();
    }

    public CartNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CartNotFoundException(String message) {
        super(message);
    }

    public CartNotFoundException(Throwable cause) {
        super(cause);
    }
}
