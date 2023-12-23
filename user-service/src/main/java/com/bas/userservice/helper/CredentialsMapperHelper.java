package com.bas.userservice.helper;

import com.bas.userservice.dto.UserDto;
import com.bas.userservice.dto.CredentialsDto;
import com.bas.userservice.entity.Credential;
import com.bas.userservice.entity.User;

public interface CredentialsMapperHelper {
    public static CredentialsDto map(final Credential credential) {
        System.out.println("a");
        return CredentialsDto.builder()
                .credentialId(credential.getCredentialId())
                .username(credential.getUsername())
                .password(credential.getPassword())
                .roleBasedAuthority(credential.getRoleBasedAuthority())
                .isEnabled(credential.getIsEnabled())
                .isAccountNonExpired(credential.getIsAccountNonExpired())
                .isAccountNonLocked(credential.getIsAccountNonLocked())
                .isCredentialsNonExpired(credential.getIsCredentialsNonExpired())
                /*.userDto(
                        UserDto.builder()
                                .userId(credential.getUser().getUserId())
                                .firstName(credential.getUser().getFirstName())
                                .lastName(credential.getUser().getLastName())
                                .imageUrl(credential.getUser().getImageUrl())
                                .email(credential.getUser().getEmail())
                                .phone(credential.getUser().getPhone())
                                .build())*/
                .build();
    }

    public static Credential map(final CredentialsDto credentialDto) {
        System.out.println("b");
        return Credential.builder()
                .credentialId(credentialDto.getCredentialId())
                .username(credentialDto.getUsername())
                .password(credentialDto.getPassword())
                .roleBasedAuthority(credentialDto.getRoleBasedAuthority())
                .isEnabled(credentialDto.getIsEnabled())
                .isAccountNonExpired(credentialDto.getIsAccountNonExpired())
                .isAccountNonLocked(credentialDto.getIsAccountNonLocked())
                .isCredentialsNonExpired(credentialDto.getIsCredentialsNonExpired())
                /*.user(
                        User.builder()
                                .userId(credentialDto.getUserDto().getUserId())
                                .firstName(credentialDto.getUserDto().getFirstName())
                                .lastName(credentialDto.getUserDto().getLastName())
                                .imageUrl(credentialDto.getUserDto().getImageUrl())
                                .email(credentialDto.getUserDto().getEmail())
                                .phone(credentialDto.getUserDto().getPhone())
                                .build())*/
                .build();
    }

}
