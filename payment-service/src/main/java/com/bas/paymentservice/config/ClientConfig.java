package com.bas.paymentservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ClientConfig {

   // @LoadBalanced
    @Bean
    public RestTemplate restTemplateBean() {
        return new RestTemplate();
    }



}
