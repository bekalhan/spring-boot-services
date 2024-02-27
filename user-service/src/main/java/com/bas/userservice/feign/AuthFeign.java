package com.bas.userservice.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("JWTAUTH-SERVICE")
public interface AuthFeign {

}