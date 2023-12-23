package com.bas.userservice.repository;

import com.bas.userservice.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address,Integer> {

    Address findByFullAddress(final String fullAddress);
}
