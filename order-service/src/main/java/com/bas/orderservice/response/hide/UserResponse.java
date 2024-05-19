package com.bas.orderservice.response.hide;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserResponse {
    private Long userId;
    private String fullname;
    private String username;
    private String email;
    private String phone;
}
