package com.challenge.addresschallenge.controller;

import com.challenge.addresschallenge.model.DTO.Address.AddressRequest;
import org.springframework.http.ResponseEntity;

public interface AddressController {

    public ResponseEntity<Object> getAll();

    public ResponseEntity<Object> createNewAddress(AddressRequest addressDto);

    public ResponseEntity<Object> updateAddress(Long addressId, AddressRequest addressDto);

    public ResponseEntity<Object> deleteAddress(Long addressId);
}
