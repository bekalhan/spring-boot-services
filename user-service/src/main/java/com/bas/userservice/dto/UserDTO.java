package com.bas.userservice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private Long userId;
    @NotNull(message = "FirstName cannot be empty")
    @Pattern(regexp = "^[A-Za-z]{2,30}$",message = "Please provide valid firstName")
    private String firstName;

    @NotNull(message = "LastName cannot be empty")
    @Pattern(regexp = "^[A-Za-z]{2,30}$",message = "Please provide valid lastName")
    private String lastName;

    @NotNull(message = "Phone number cannot be empty")
    @Pattern(regexp = "^\\+91-[1-9]\\d{9}$",message = "Please provide valid phone number")
    private String phNo;

    private String username;

    @Valid
    private List<AddressDTO> addressDTOList;

    @Valid
    private List<CardDTO>cardDTOList;
}
