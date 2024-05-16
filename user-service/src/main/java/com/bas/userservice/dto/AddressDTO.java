package com.bas.userservice.dto;

import com.bas.userservice.entity.enums.AddressType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDTO {

    @NotNull(message = "Name cannot be empty")
    @Pattern(regexp = "^[A-Za-z]{2,30}$",message = "Please provide valid name")
    private String name;

    @NotNull(message = "Phone number cannot be empty")
    @Pattern(regexp = "^\\+91-[1-9]\\d{9}$",message = "Please provide valid phone number")
    private String phNo;

    @NotNull(message = "PinCode cannot be empty")
    @Pattern(regexp = "^\\d{6}$",message = "Please provide valid pinCode")
    private String pinCode;

    @NotNull(message = "State cannot be empty")
    @Pattern(regexp = "^[A-Za-z]{2,30}$",message = "Please provide valid state")
    private String state;

    @NotNull(message = "City cannot be empty")
    @Pattern(regexp = "^[A-Za-z]{2,30}$",message = "Please provide valid city")
    private String city;

    @Valid
    @NotNull(message = "Address type cannot be empty")
    private AddressType addressType;
}
