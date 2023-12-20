package com.bas.userservice.service;

import com.bas.userservice.dto.CredentialsDto;

import java.util.List;

public interface CredentialsService {
    List<CredentialsDto> findAll();
    CredentialsDto findById(final Integer credentialId);
    CredentialsDto save(final CredentialsDto credentialsDto);
    CredentialsDto update(final CredentialsDto credentialsDto);
    CredentialsDto update(final Integer credentialId,final CredentialsDto credentialsDto);
    void deleteById(final Integer credentialId);
    CredentialsDto findByUsername(final String username);
}
