package com.bas.userservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class UserDto {
    private Integer userId;

    @NotEmpty(message="Please provide valid first name")
    private String firstName;
    @NotEmpty(message="Please provide valid last name")
    private String lastName;

    private String imageUrl;
//    @NotEmpty(message = "title should not be null or empty")
    @Email(message="Please provide valid email")
    private String email;
    @Pattern(regexp = "[+]\\d{10}", message = "Please provide valid phone number")
    private String phone;

    //@JsonProperty("addresses")  // JsonProperty ekledik
    private Set<AddressDto> addressDtos = new HashSet<>();

    @JsonProperty("credential")
    private CredentialsDto credentialDto;
}
