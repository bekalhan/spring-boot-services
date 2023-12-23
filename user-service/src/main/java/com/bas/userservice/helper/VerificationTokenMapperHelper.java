package com.bas.userservice.helper;

import com.bas.userservice.dto.AddressDto;
import com.bas.userservice.dto.CredentialsDto;
import com.bas.userservice.dto.UserDto;
import com.bas.userservice.dto.VerificationTokenDto;
import com.bas.userservice.entity.Address;
import com.bas.userservice.entity.Credential;
import com.bas.userservice.entity.User;
import com.bas.userservice.entity.VerificationToken;

public interface VerificationTokenMapperHelper {
    public static VerificationTokenDto map(final VerificationToken verificationToken) {
        return VerificationTokenDto.builder()
                .verificationTokenId(verificationToken.getVerificationTokenId())
                .token(verificationToken.getToken())
                .expireDate(verificationToken.getExpireDate())
                .credentialDto(
                        CredentialsDto.builder()
                                .credentialId(verificationToken.getCredential().getCredentialId())
                                .username(verificationToken.getCredential().getUsername())
                                .password(verificationToken.getCredential().getPassword())
                                .roleBasedAuthority(verificationToken.getCredential().getRoleBasedAuthority())
                                .isEnabled(verificationToken.getCredential().getIsEnabled())
                                .isAccountNonExpired(verificationToken.getCredential().getIsAccountNonExpired())
                                .isAccountNonLocked(verificationToken.getCredential().getIsAccountNonLocked())
                                .isCredentialsNonExpired(verificationToken.getCredential().getIsCredentialsNonExpired())
                                .build())
                .build();
    }

    public static VerificationToken map(final VerificationTokenDto verificationTokenDto) {
        return VerificationToken.builder()
                .verificationTokenId(verificationTokenDto.getVerificationTokenId())
                .token(verificationTokenDto.getToken())
                .expireDate(verificationTokenDto.getExpireDate())
                .credential(
                        Credential.builder()
                                .credentialId(verificationTokenDto.getCredentialDto().getCredentialId())
                                .username(verificationTokenDto.getCredentialDto().getUsername())
                                .password(verificationTokenDto.getCredentialDto().getPassword())
                                .roleBasedAuthority(verificationTokenDto.getCredentialDto().getRoleBasedAuthority())
                                .isEnabled(verificationTokenDto.getCredentialDto().getIsEnabled())
                                .isAccountNonExpired(verificationTokenDto.getCredentialDto().getIsAccountNonExpired())
                                .isAccountNonLocked(verificationTokenDto.getCredentialDto().getIsAccountNonLocked())
                                .isCredentialsNonExpired(verificationTokenDto.getCredentialDto().getIsCredentialsNonExpired())
                                .build())
                .build();
    }


}
