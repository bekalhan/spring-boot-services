package com.bas.productservice.exception;

public class CategoryNotFound extends RuntimeException {
    public CategoryNotFound(String msg){
        super(msg);
    }
}
