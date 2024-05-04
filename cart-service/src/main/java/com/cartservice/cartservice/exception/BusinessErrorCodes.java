package com.cartservice.cartservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BusinessErrorCodes {

    PRODUCT_NOT_FOUND(404, HttpStatus.NOT_FOUND,"Product not found"),

    ;
    private int code;
    private String description;
    private HttpStatus httpStatus;

    BusinessErrorCodes(int code, HttpStatus httpStatus,String description) {
        this.code = code;
        this.description = description;
        this.httpStatus = httpStatus;
    }
}
