package com.bas.orderservice.exception;

public class OrderNotExist extends RuntimeException{
    public OrderNotExist(String s) {
        super(s);
    }
}
