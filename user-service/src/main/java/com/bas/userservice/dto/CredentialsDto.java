package com.bas.userservice.dto;

import com.bas.userservice.entity.RoleBasedAuthority;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Getter
@Setter
public class CredentialsDto {
    private Integer credentialId;

    private String username;

    private String password;

    private RoleBasedAuthority roleBasedAuthority;

    private Boolean isEnabled;

    private Boolean isAccountNonExpired;

    private Boolean isAccountNonLocked;

    private Boolean isCredentialsNonExpired;

    //@JsonProperty("user")
    //@JsonInclude(value = JsonInclude.Include.NON_NULL)
    private UserDto userDto;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private Set<VerificationTokenDto> verificationTokenDtos;
}
