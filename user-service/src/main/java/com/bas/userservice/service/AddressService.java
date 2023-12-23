package com.bas.userservice.service;

import com.bas.userservice.dto.AddressDto;

import java.util.List;

public interface AddressService {
    List<AddressDto> findAll();
    AddressDto findById(final Integer addressId);
    AddressDto save(final AddressDto addressDto);
    AddressDto update(final AddressDto addressDto);
    AddressDto update(final Integer addressId,final AddressDto addressDto);
    void deleteById(final Integer addressId);
}
