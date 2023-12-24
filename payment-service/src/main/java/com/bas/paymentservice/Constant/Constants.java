package com.bas.paymentservice.Constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {
    public static final String LOCAL_DATE_FORMAT = "dd-MM-yyyy";
    public static final String LOCAL_DATE_TIME_FORMAT = "dd-MM-yyyy__HH:mm:ss:SSSSSS";
    public static final String ZONED_DATE_TIME_FORMAT = "dd-MM-yyyy__HH:mm:ss:SSSSSS";
    public static final String INSTANT_FORMAT = "dd-MM-yyyy__HH:mm:ss:SSSSSS";


    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public abstract class Domains {

        public static final String PRODUCT_SERVICE_HOST = "http://localhost:8500/api/products";
        public static final String PRODUCT_SERVICE_API_URL = "http://localhost:8500/api/products";

        public static final String ORDER_SERVICE_HOST = "http://localhost:8600/api/orders";
        public static final String ORDER_SERVICE_API_URL = "http://ORDER-SERVICE/api/orders";

        public static final String PAYMENT_SERVICE_HOST = "http://localhost:8400/payment-service";
        public static final String PAYMENT_SERVICE_API_URL = "http://localhost:8400/payment-service/api/payments";

    }
}
