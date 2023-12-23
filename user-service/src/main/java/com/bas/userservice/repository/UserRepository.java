package com.bas.userservice.repository;

import com.bas.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByCredentialUsername(final String username);

    User findByEmail(final String email);
}
