package com.bas.userservice.service.impl;

import com.bas.userservice.dto.AddressDto;
import com.bas.userservice.entity.Address;
import com.bas.userservice.entity.User;
import com.bas.userservice.exception.ResourceNotFoundException;
import com.bas.userservice.helper.AddressMapperHelper;
import com.bas.userservice.repository.AddressRepository;
import com.bas.userservice.repository.UserRepository;
import com.bas.userservice.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    @Override
    public List<AddressDto> findAll() {
        return addressRepository.findAll().stream().map(AddressMapperHelper::map).distinct().collect(Collectors.toList());
    }

    @Override
    public AddressDto findById(Integer addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(()->new ResourceNotFoundException("Address","id",addressId));

        return AddressMapperHelper.map(address);
    }

    @Override
    public AddressDto save(AddressDto addressDto) {
        User findUser = userRepository.findById(addressDto.getUserId())
                .orElseThrow(()->new ResourceNotFoundException("User","id",addressDto.getUserId()));

        System.out.println("adress"+addressDto);

        return AddressMapperHelper.map(this.addressRepository.save(AddressMapperHelper.map(addressDto,findUser)));
    }

    @Override
    public AddressDto update(AddressDto addressDto) {
        User findUser = userRepository.findById(addressDto.getUserId())
                .orElseThrow(()->new ResourceNotFoundException("User","id",addressDto.getUserId()));

        return AddressMapperHelper.map(this.addressRepository.save(AddressMapperHelper.map(addressDto,findUser)));
    }

    @Override
    public AddressDto update(Integer addressId, AddressDto addressDto) {
        User findUser = userRepository.findById(addressDto.getUserId())
                .orElseThrow(()->new ResourceNotFoundException("User","id",addressDto.getUserId()));

        Address address = this.addressRepository.findById(addressId)
                .orElseThrow(()-> new ResourceNotFoundException("Address","id",addressId));

        Address mapToAddress = AddressMapperHelper.map(addressDto,findUser);
        Address updatedAddress = addressRepository.save(mapToAddress);

        return AddressMapperHelper.map(updatedAddress);
    }

    @Override
    public void deleteById(Integer addressId) {
        Address address = this.addressRepository.findById(addressId)
                .orElseThrow(()-> new ResourceNotFoundException("Address","id",addressId));

        this.addressRepository.findById(addressId);
    }
}
