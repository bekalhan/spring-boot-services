package com.bas.userservice.exception;

public class CartEmptyException extends Exception{
    public CartEmptyException(String msg){
        super(msg);
    }
}
