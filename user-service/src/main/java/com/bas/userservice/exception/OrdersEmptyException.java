package com.bas.userservice.exception;

public class OrdersEmptyException extends Exception{
    public OrdersEmptyException(String msg){
        super(msg);
    }
}
