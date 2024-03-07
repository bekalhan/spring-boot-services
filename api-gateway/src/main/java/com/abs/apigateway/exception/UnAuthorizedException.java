package com.abs.apigateway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.UNAUTHORIZED)
public class UnAuthorizedException extends RuntimeException{
    private String message;

    public UnAuthorizedException(String message){
        this.message = message;
    }
}
