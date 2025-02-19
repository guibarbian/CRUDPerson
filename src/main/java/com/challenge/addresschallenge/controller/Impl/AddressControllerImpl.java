package com.challenge.addresschallenge.controller.Impl;

import com.challenge.addresschallenge.controller.AddressController;
import com.challenge.addresschallenge.model.DTO.Address.AddressRequest;
import com.challenge.addresschallenge.service.impl.AddressServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressControllerImpl implements AddressController {

    private final AddressServiceImpl addressService;

    @GetMapping
    @Override
    public ResponseEntity<Object> getAll(){
        return ResponseEntity.ok().body(addressService.getAll());
    }

    @PostMapping
    @Override
    public ResponseEntity<Object> createNewAddress(AddressRequest addressDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(addressService.createNewAddress(addressDto));
    }

    @PutMapping("/{addressId}")
    @Override
    public ResponseEntity<Object> updateAddress(@PathVariable Long addressId,
                                                @RequestBody AddressRequest addressDto){
        return ResponseEntity.ok().body(addressService.updateAddress(addressId, addressDto));
    }

    @DeleteMapping("/{addressId}")
    @Override
    public ResponseEntity<Object> deleteAddress(@PathVariable Long addressId){
        addressService.deleteAddress(addressId);
        return ResponseEntity.ok().body("Address deleted.");
    }
}
