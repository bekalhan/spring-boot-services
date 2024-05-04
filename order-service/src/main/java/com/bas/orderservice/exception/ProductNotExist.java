package com.bas.orderservice.exception;

public class ProductNotExist extends RuntimeException{
    public ProductNotExist(String s) {
        super(s);
    }
}
