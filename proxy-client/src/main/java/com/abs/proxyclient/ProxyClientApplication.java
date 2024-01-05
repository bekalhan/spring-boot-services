package com.abs.proxyclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ProxyClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxyClientApplication.class, args);
	}

}
