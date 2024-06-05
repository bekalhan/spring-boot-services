package com.bas.orderservice.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserResponse {
    private Integer Id;
    private String firstName;
    private String lastName;
}
