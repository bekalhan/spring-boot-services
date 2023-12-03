package com.bas.productservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix="accounts")
@Getter
@Setter
public class ProductContactInfoDtos {
    private String message;
    private Map<String,String> contactDetails;
    private List<String> onCallSupport;
}
