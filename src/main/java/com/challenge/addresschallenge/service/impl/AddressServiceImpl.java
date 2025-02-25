package com.challenge.addresschallenge.service.impl;

import com.challenge.addresschallenge.exception.BadRequestException;
import com.challenge.addresschallenge.exception.NotFoundException;
import com.challenge.addresschallenge.model.Address;
import com.challenge.addresschallenge.model.DTO.Address.AddressRequest;
import com.challenge.addresschallenge.model.DTO.Address.AddressResponse;
import com.challenge.addresschallenge.repository.AddressRepository;
import com.challenge.addresschallenge.service.AddressService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public List<Address> getAddresses(){
        return addressRepository.findAll();
    }

    @Override
    public AddressResponse createNewAddress(AddressRequest addressDto){

        addressRepository.save(
                Address.builder()
                        .street(addressDto.getStreet())
                        .number(addressDto.getNumber())
                        .neighbourhood(addressDto.getNeighbourhood())
                        .city(addressDto.getCity())
                        .state(addressDto.getState())
                        .zipCode(addressDto.getZipCode())
                        .build()
        );

        return AddressResponse.builder()
                .street(addressDto.getStreet())
                .number(addressDto.getNumber())
                .neighbourhood(addressDto.getNeighbourhood())
                .city(addressDto.getCity())
                .state(addressDto.getState())
                .zipCode(addressDto.getZipCode())
                .build();
    }

    @Override
    public AddressResponse updateAddress(Long addressId, AddressRequest addressDto){
        validateUpdateAddress(addressId);

        addressRepository.save(
                Address.builder()
                        .id(addressId)
                        .street(addressDto.getStreet())
                        .number(addressDto.getNumber())
                        .neighbourhood(addressDto.getNeighbourhood())
                        .city(addressDto.getCity())
                        .state(addressDto.getState())
                        .zipCode(addressDto.getZipCode())
                        .build()
        );

        return AddressResponse.builder()
                .street(addressDto.getStreet())
                .number(addressDto.getNumber())
                .neighbourhood(addressDto.getNeighbourhood())
                .city(addressDto.getCity())
                .state(addressDto.getState())
                .zipCode(addressDto.getZipCode())
                .build();

    }

    public void deleteAddress(Long addressId){
        validateUpdateAddress(addressId);

        addressRepository.deleteById(addressId);
    }

    // FUNCTIONS

    public void validateUpdateAddress(Long addressId){
        if(addressRepository.findById(addressId).isEmpty()){
            throw new NotFoundException("Address Not Found");
        }
    }

    public Address getAddressById(Long addressId){
        if(addressRepository.findById(addressId).isEmpty()){
            throw new NotFoundException("Address was not found.");
        }

        return addressRepository.findById(addressId).get();
    }

    public List<Address> getAddressesById(List<Long> addressesId){
        if(addressesId.isEmpty()){
            throw new BadRequestException("A person must have a least one address.");
        }

        ArrayList<Address> list = new ArrayList<>();

        addressesId.forEach(id -> {

            if(addressRepository.findById(id).isEmpty()){
                throw new NotFoundException("Address of id: " + id + " not found.");
            }

            list.add(addressRepository.findById(id).get());
        });

        return list;
    }

    public void deletePersonsAddressess(List<Address> addresses){
        addresses.forEach(address -> {
            addressRepository.deleteById(address.getId());
        });
    }

}
