package com.bas.orderservice.exception;

public class ProductNotFound extends RuntimeException{

    public ProductNotFound(String msg){
        super(msg);
    }
}
