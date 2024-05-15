package com.cartservice.cartservice.exception;

public class CartItemNotExist extends RuntimeException{
    public CartItemNotExist(String s) {
        super(s);
    }
}
