package com.abs.proxyclient.business.auth.service.impl;

import com.abs.proxyclient.business.user.model.CredentialDto;
import com.abs.proxyclient.business.user.model.UserDetailsImpl;
import com.abs.proxyclient.constants.AppConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final String API_URL = AppConstant.DiscoveredDomainsApi.USER_SERVICE_HOST + "/api/credentials";
    private final RestTemplate restTemplate;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        log.info("**UserDetails, load user by username*\n");
        return new UserDetailsImpl(this.restTemplate.getForObject(API_URL + "/username/" + username, CredentialDto.class));
    }
}
