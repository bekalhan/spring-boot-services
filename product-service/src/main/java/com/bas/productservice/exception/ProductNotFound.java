package com.bas.productservice.exception;

public class ProductNotFound extends RuntimeException{

    public ProductNotFound(String msg){
        super(msg);
    }
}
