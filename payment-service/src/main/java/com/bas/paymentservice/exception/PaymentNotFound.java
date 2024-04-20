package com.bas.paymentservice.exception;

public class PaymentNotFound extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public PaymentNotFound() {
        super();
    }

    public PaymentNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public PaymentNotFound(String message) {
        super(message);
    }

    public PaymentNotFound(Throwable cause) {
        super(cause);
    }

}
