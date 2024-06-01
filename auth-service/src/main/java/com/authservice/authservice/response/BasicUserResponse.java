package com.authservice.authservice.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BasicUserResponse {
    private Integer id;
    private String firstName;
    private String lastName;
}
