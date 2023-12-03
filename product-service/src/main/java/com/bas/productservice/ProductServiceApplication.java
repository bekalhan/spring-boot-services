package com.bas.productservice;

import com.bas.productservice.dto.ProductContactInfoDtos;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value={ProductContactInfoDtos.class})
@OpenAPIDefinition(
		info = @Info(
				title = "Products microservice REST API Documentation",
				description = "abs Products microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Aytuğ/Berat/Sefa",
						email = "abs@gmail.com"
				),
				license = @License(
						name = "Apache 2.0"
				)
		),
		externalDocs = @ExternalDocumentation(
				description =  "abs Products microservice REST API Documentation"
		)
)
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

}
