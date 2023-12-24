package com.bas.paymentservice.Config.Client;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableJpaAuditing
public class ClientConfig {
    @Bean
    @LoadBalanced
    public RestTemplate restTemplateBean() {
        return new RestTemplate();
    }
}
