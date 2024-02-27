package com.bas.userservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardDTO {

    @NotNull(message = "Card number cannot be empty")
    @Pattern(regexp = "^\\d{12}$",message = "Please provide valid card number")
    private String cardNumber;

    @NotNull(message = "Name on card cannot be empty")
    @Pattern(regexp = "^[A-Za-z]{2,30}$",message = "Please provide valid name")
    private String nameOnCard;
}