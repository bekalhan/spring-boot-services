package com.abs.apigateway.filter;


import com.abs.apigateway.dto.UserDTO;
import com.abs.apigateway.exception.UnAuthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.file.AccessDeniedException;


@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;

    @Autowired
    private RestTemplate restTemplate;

    private final WebClient.Builder webClientBuilder;


    public AuthenticationFilter(WebClient.Builder webClientBuilder) {
        super(Config.class);
        this.webClientBuilder = webClientBuilder;
    }

   /* @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {

            if (validator.isSecured.test(exchange.getRequest())) {
                //header contains token or not
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("missing authorization header");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }
                System.out.println("header"+authHeader);
                var returntt = webClientBuilder.build()
                        .get()
                        .uri("http://AUTH-SERVICE/auth/validateTkn?jwt=" + authHeader)
                        .retrieve().bodyToMono(UserDTO.class)
                        .map(userDTO -> {
                            exchange.getRequest()
                                    .mutate()
                                    .header("currUser",userDTO.getUsername());
                            return exchange;
                        }).flatMap(chain::filter);
                System.out.println("returntt"+returntt);
                return returntt;
            }
            System.out.println("exx");
            return chain.filter(exchange);
        });
    }*/

    @Override
    public GatewayFilter apply(Config config) {
        System.out.println("entered");
        return (exchange, chain) -> {
            System.out.println("chain");
            if (validator.isSecured.test(exchange.getRequest())) {
                System.out.println("secured");
                // Header contains token or not
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("missing authorization header");
                }
                System.out.println("a");
                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                } else {
                    throw new RuntimeException("incorrect authorization header format");
                }
                System.out.println("b" + authHeader);

                try {
                    System.out.println("Validating token: " + authHeader);
                    restTemplate.getForObject("http://authservice:8081/auth/validateTkn?jwt=" + authHeader, UserDTO.class);
                    System.out.println("Token validated successfully");
                } catch (Exception e) {
                    System.out.println("Token validation failed: " + e.getMessage());
                    throw new UnAuthorizedException("Unauthorized access to application");
                }
            }
            return chain.filter(exchange);
        };
    }

    public static class Config {

    }
}