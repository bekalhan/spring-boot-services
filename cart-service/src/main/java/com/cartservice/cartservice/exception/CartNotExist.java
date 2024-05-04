package com.cartservice.cartservice.exception;

public class CartNotExist extends RuntimeException{
    public CartNotExist(String s) {
        super(s);
    }
}
