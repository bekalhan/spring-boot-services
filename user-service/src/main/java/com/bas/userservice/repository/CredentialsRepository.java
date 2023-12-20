package com.bas.userservice.repository;

import com.bas.userservice.entity.Credential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CredentialsRepository extends JpaRepository<Credential,Integer> {
    Optional<Credential> findByUsername(final String username);
}
