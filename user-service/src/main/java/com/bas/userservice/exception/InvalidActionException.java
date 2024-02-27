package com.bas.userservice.exception;

public class InvalidActionException extends RuntimeException{
    public InvalidActionException(String msg){
        super(msg);
    }
}
