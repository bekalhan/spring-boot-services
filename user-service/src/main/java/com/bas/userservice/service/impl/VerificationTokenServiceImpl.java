package com.bas.userservice.service.impl;

import com.bas.userservice.dto.VerificationTokenDto;
import com.bas.userservice.entity.VerificationToken;
import com.bas.userservice.exception.ResourceNotFoundException;
import com.bas.userservice.helper.VerificationTokenMapperHelper;
import com.bas.userservice.repository.VerificationTokenRepository;
import com.bas.userservice.service.VerificationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VerificationTokenServiceImpl implements VerificationTokenService {

    private final VerificationTokenRepository verificationTokenRepository;

    @Override
    public List<VerificationTokenDto> findAll() {
        return verificationTokenRepository.findAll().stream().map(VerificationTokenMapperHelper::map).distinct()
                .collect(Collectors.toList());
    }

    @Override
    public VerificationTokenDto findById(Integer verificationTokenId) {
        VerificationToken verificationToken = verificationTokenRepository.findById(verificationTokenId)
                .orElseThrow(()->new ResourceNotFoundException("VerificationToken","id",verificationTokenId));

        return VerificationTokenMapperHelper.map(verificationToken);
    }

    @Override
    public VerificationTokenDto save(VerificationTokenDto verificationTokenDto) {
        return VerificationTokenMapperHelper.map(this.verificationTokenRepository.save(VerificationTokenMapperHelper.map(verificationTokenDto)));
    }

    @Override
    public VerificationTokenDto update(VerificationTokenDto verificationTokenDto) {
        return VerificationTokenMapperHelper.map(this.verificationTokenRepository.save(VerificationTokenMapperHelper.map(verificationTokenDto)));
    }

    @Override
    public VerificationTokenDto update(Integer verificationTokenId, VerificationTokenDto verificationTokenDto) {
        VerificationToken verificationToken = verificationTokenRepository.findById(verificationTokenId)
                .orElseThrow(()->new ResourceNotFoundException("VerificationToken","id",verificationTokenId));

        VerificationToken mapToVerificationToken = VerificationTokenMapperHelper.map(verificationTokenDto);
        VerificationToken updatedVerificationToken = verificationTokenRepository.save(mapToVerificationToken);

        return VerificationTokenMapperHelper.map(updatedVerificationToken);
    }

    @Override
    public void deleteById(Integer verificationTokenId) {
        this.verificationTokenRepository.deleteById(verificationTokenId);
    }
}
