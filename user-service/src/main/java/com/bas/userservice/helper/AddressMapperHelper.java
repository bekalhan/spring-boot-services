package com.bas.userservice.helper;

import com.bas.userservice.dto.AddressDto;
import com.bas.userservice.dto.UserDto;
import com.bas.userservice.entity.Address;
import com.bas.userservice.entity.User;

public interface AddressMapperHelper {
    public static AddressDto map(final Address address){
        return AddressDto.builder()
                .addressId(address.getAddressId())
                .fullAddress(address.getFullAddress())
                .postalCode(address.getPostalCode())
                .city(address.getCity())
                .userId(address.getUser().getUserId())
                /*.userDto(
                        UserDto.builder()
                                .userId(address.getUser().getUserId())
                                .firstName(address.getUser().getFirstName())
                                .lastName(address.getUser().getLastName())
                                .imageUrl(address.getUser().getImageUrl())
                                .email(address.getUser().getEmail())
                                .phone(address.getUser().getPhone())
                                .build()
                )*/
                .build();
    }

    public static Address map(final AddressDto addressDto,User user){
        return Address.builder()
                .addressId(addressDto.getAddressId())
                .fullAddress(addressDto.getFullAddress())
                .postalCode(addressDto.getPostalCode())
                .city(addressDto.getCity())
                .user(user)
                /*.user(
                        User.builder()
                                .userId(addressDto.getUserDto().getUserId())
                                .firstName(addressDto.getUserDto().getFirstName())
                                .lastName(addressDto.getUserDto().getLastName())
                                .imageUrl(addressDto.getUserDto().getImageUrl())
                                .email(addressDto.getUserDto().getEmail())
                                .phone(addressDto.getUserDto().getPhone())
                                .build())*/
                .build();


    }
}
