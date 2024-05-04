package com.bas.orderservice.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class AppConstant {

    public static final String LOCAL_DATE_FORMAT = "dd-MM-yyyy";
    public static final String LOCAL_DATE_TIME_FORMAT = "dd-MM-yyyy__HH:mm:ss:SSSSSS";
    public static final String ZONED_DATE_TIME_FORMAT = "dd-MM-yyyy__HH:mm:ss:SSSSSS";
    public static final String INSTANT_FORMAT = "dd-MM-yyyy__HH:mm:ss:SSSSSS";

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public abstract class DiscoveredDomainsApi {

        public static final String USER_SERVICE_HOST = "http://localhost:8700/user-service";
        public static final String USER_SERVICE_API_URL = "http://USER-SERVICE/api/users";
        public static final String PRODUCT_SERVICE_HOST = "http://PRODUCT-SERVICE/product-service";
        public static final String PRODUCT_SERVICE_API_URL = "http://PRODUCT-SERVICE/product-service/api/products";
    }



}
