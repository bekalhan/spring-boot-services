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
                try {
                    restTemplate.getForObject("http://AUTH-SERVICE/auth/validateTkn?jwt=" + authHeader, UserDTO.class);
                } catch (Exception e) {
                    System.out.println("invalid access...!");
                    //throw new RuntimeException("un authorized access to application");
                    throw new UnAuthorizedException("you have no access to this route");
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class Config {

    }
}