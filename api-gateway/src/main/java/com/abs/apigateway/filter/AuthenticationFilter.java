package com.abs.apigateway.filter;


import com.abs.apigateway.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;


@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;

    private final WebClient.Builder webClientBuilder;


    public AuthenticationFilter(WebClient.Builder webClientBuilder) {
        super(Config.class);
        this.webClientBuilder = webClientBuilder;
    }

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

                return webClientBuilder.build()
                        .get()
                        .uri("http://AUTH-SERVICE/auth/validateTkn?jwt=" + authHeader)
                        .retrieve().bodyToMono(UserDTO.class)
                        .map(userDTO -> {
                            exchange.getRequest()
                                    .mutate()
                                    .header("currUser",userDTO.getUsername());
                            return exchange;
                        }).flatMap(chain::filter);
            }
            return chain.filter(exchange);
        });
    }

    public static class Config {

    }
}