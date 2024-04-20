package com.bas.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserDto {
    private Long userId;
    private String fullname;
    private String username;
    private String email;
    private String gender;
    private String phone;
    private String avatar;
}
