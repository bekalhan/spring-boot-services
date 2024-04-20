package com.bas.productservice.exception;

public class ItemAlreadyExist extends RuntimeException{

    public ItemAlreadyExist(String msg){
        super(msg);
    }
    public ItemAlreadyExist(String msg , Throwable cause){
        super(msg,cause);
    }
    public ItemAlreadyExist(String msg,String item){
        super(msg+" : "+item);
    }
}
