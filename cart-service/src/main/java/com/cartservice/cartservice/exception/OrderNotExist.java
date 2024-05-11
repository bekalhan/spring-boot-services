package com.cartservice.cartservice.exception;

public class OrderNotExist extends RuntimeException{
    public OrderNotExist(String s) {
        super(s);
    }
}
