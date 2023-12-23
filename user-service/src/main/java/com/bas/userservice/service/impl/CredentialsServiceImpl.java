package com.bas.userservice.service.impl;

import com.bas.userservice.dto.CredentialsDto;
import com.bas.userservice.entity.Credential;
import com.bas.userservice.exception.ResourceNotFoundException;
import com.bas.userservice.helper.CredentialsMapperHelper;
import com.bas.userservice.repository.CredentialsRepository;
import com.bas.userservice.service.CredentialsService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CredentialsServiceImpl implements CredentialsService {

    private final CredentialsRepository credentialsRepository;

    @Override
    public List<CredentialsDto> findAll() {
        return credentialsRepository.findAll().stream().map(CredentialsMapperHelper::map).distinct()
                .collect(Collectors.toList());
    }

    @Override
    public CredentialsDto findById(Integer credentialId) {
        Credential credential = credentialsRepository.findById(credentialId)
                .orElseThrow(()->new ResourceNotFoundException("Credential","id",credentialId));

        return CredentialsMapperHelper.map(credential);
    }

    @Override
    public CredentialsDto save(CredentialsDto credentialsDto) {
        return CredentialsMapperHelper.map(this.credentialsRepository.save(CredentialsMapperHelper.map(credentialsDto)));
    }

    @Override
    public CredentialsDto update(CredentialsDto credentialsDto) {
        return CredentialsMapperHelper.map(this.credentialsRepository.save(CredentialsMapperHelper.map(credentialsDto)));
    }

    @Override
    public CredentialsDto update(Integer credentialId, CredentialsDto credentialsDto) {
        Credential credential = credentialsRepository.findById(credentialId)
                .orElseThrow(()-> new ResourceNotFoundException("Credential","id",credentialId));

        Credential mapToCredential = CredentialsMapperHelper.map(credentialsDto);
        Credential updatedCredential = credentialsRepository.save(mapToCredential);

        return CredentialsMapperHelper.map(updatedCredential);
    }

    @Override
    public void deleteById(Integer credentialId) {
        Credential credential = credentialsRepository.findById(credentialId)
                .orElseThrow(()-> new ResourceNotFoundException("Credential","id",credentialId));
        this.credentialsRepository.deleteById(credentialId);
    }

    @Override
    public CredentialsDto findByUsername(String username) {
        return CredentialsMapperHelper.map(this.credentialsRepository.findByUsername(username)
                .orElseThrow(()->new ResourceNotFoundException("Credential","username",username))
        );
    }
}
