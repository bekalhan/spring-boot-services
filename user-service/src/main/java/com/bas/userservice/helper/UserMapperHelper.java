package com.bas.userservice.helper;

import com.bas.userservice.dto.UserDto;
import com.bas.userservice.dto.CredentialsDto;
import com.bas.userservice.entity.Credential;
import com.bas.userservice.entity.User;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

public interface UserMapperHelper {
    public static UserDto map(final User user) {

        System.out.println("user"+user);

        return UserDto.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .imageUrl(user.getImageUrl())
                .email(user.getEmail())
                .phone(user.getPhone())
                /*.addressDtos(Optional.ofNullable(user.getAddresses())
                .orElse(Collections.emptySet())
                .stream()
                .map(AddressMapperHelper::map)
                .collect(Collectors.toSet()))*/
                .credentialDto(
                        CredentialsDto.builder()
                                .credentialId(user.getCredential().getCredentialId())
                                .username(user.getCredential().getUsername())
                                .password(user.getCredential().getPassword())
                                .roleBasedAuthority(user.getCredential().getRoleBasedAuthority())
                                .isEnabled(user.getCredential().getIsEnabled())
                                .isAccountNonExpired(user.getCredential().getIsAccountNonExpired())
                                .isAccountNonLocked(user.getCredential().getIsAccountNonLocked())
                                .isCredentialsNonExpired(user.getCredential().getIsCredentialsNonExpired())
                                .build())
                .build();
    }

    public static User map(final UserDto userDto) {
        System.out.println("g"+userDto);
        return User.builder()
                .userId(userDto.getUserId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .imageUrl(userDto.getImageUrl())
                .email(userDto.getEmail())
                .phone(userDto.getPhone())
                .credential(
                        Credential.builder()
                                .credentialId(userDto.getUserId())
                                .username(userDto.getCredentialDto().getUsername())
                                .password(userDto.getCredentialDto().getPassword())
                                .roleBasedAuthority(userDto.getCredentialDto().getRoleBasedAuthority())
                                .isEnabled(userDto.getCredentialDto().getIsEnabled())
                                .isAccountNonExpired(userDto.getCredentialDto().getIsAccountNonExpired())
                                .isAccountNonLocked(userDto.getCredentialDto().getIsAccountNonLocked())
                                .isCredentialsNonExpired(userDto.getCredentialDto().getIsCredentialsNonExpired())
                                .build())
                .build();
    }

}
