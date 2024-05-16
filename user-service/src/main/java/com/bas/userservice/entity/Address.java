package com.bas.userservice.entity;

import com.bas.userservice.entity.enums.AddressType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    private String name;
    private String phNo;
    private String pinCode;
    private String state;
    private String city;
    @Enumerated(EnumType.STRING)
    private AddressType addressType;
}
