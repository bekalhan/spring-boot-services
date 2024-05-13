package com.abs.apigateway.exception;


import com.abs.apigateway.exception.payload.ErrorDetails;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.server.ServerWebExchange;

import java.nio.file.AccessDeniedException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

   @ExceptionHandler(UnAuthorizedException.class)
   public ResponseEntity<ErrorDetails> handleUnAuthorizedException(UnAuthorizedException ex, ServerWebExchange exchange) {
       ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), exchange.getRequest().getPath().toString());
       return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
   }


}
