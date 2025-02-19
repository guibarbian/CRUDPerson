package com.challenge.addresschallenge.service;

import com.challenge.addresschallenge.model.Address;
import com.challenge.addresschallenge.model.DTO.Address.AddressRequest;
import com.challenge.addresschallenge.model.DTO.Address.AddressResponse;

import java.util.List;

public interface AddressService {

    public List<Address> getAll();

    public AddressResponse createNewAddress(AddressRequest addressDto);

    public AddressResponse updateAddress(Long addressId, AddressRequest addressDto);

    public void deleteAddress(Long addressId);
}
