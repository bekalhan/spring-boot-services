package com.bas.userservice.controller;

import com.bas.userservice.dto.AddressDto;
import com.bas.userservice.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
@AllArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping
    public ResponseEntity<List<AddressDto>> getAllAdresses(){
        List<AddressDto> allAdresses = addressService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(allAdresses);
    }

    @GetMapping("/{addressId12}")
    public ResponseEntity<AddressDto> getAddressById(@PathVariable(value="addressId") Integer addressId){
        AddressDto address = addressService.findById(addressId);
        return ResponseEntity.status(HttpStatus.OK).body(address);
    }

    @PostMapping
    public ResponseEntity<AddressDto> save(@RequestBody AddressDto addressDto){
        System.out.println("gir"+addressDto);
        AddressDto address = addressService.save(addressDto);
        System.out.println("address2"+addressDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(address);
    }

    @PutMapping
    public ResponseEntity<AddressDto> update(@RequestBody AddressDto addressDto){
        AddressDto address = addressService.update(addressDto);
        return ResponseEntity.status(HttpStatus.OK).body(address);
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<AddressDto> update(@PathVariable(value="addressId") Integer addressId,@RequestBody AddressDto addressDto){
        AddressDto address = addressService.update(addressId,addressDto);
        return ResponseEntity.status(HttpStatus.OK).body(address);
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Integer addressId){
        this.addressService.deleteById(addressId);
        return ResponseEntity.ok(true);
    }

}
