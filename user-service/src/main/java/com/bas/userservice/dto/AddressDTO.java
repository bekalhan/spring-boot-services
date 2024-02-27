package com.bas.userservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AddressDto {

    private Integer addressId;

    @NotEmpty(message="Please provide valid address")
    private String fullAddress;

    @NotEmpty(message="Please provide valid postal code")
    private String postalCode;

    @NotEmpty(message="Please provide valid city")
    private String city;

    //@JsonProperty("user")
    //@JsonInclude(value = JsonInclude.Include.NON_NULL)
    private Integer userId;
}
