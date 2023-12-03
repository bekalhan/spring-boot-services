package com.bas.orderservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableJpaAuditing
public class Config {
    @Bean
    public RestTemplate restTemplateBean() {
        return new RestTemplate();
    }
}
