package com.challenge.addresschallenge.service.impl;

import com.challenge.addresschallenge.exception.NotFoundException;
import com.challenge.addresschallenge.model.Address;
import com.challenge.addresschallenge.model.DTO.Address.AddressRequest;
import com.challenge.addresschallenge.model.DTO.Address.AddressResponse;
import com.challenge.addresschallenge.model.Person;
import com.challenge.addresschallenge.repository.AddressRepository;
import com.challenge.addresschallenge.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final PersonServiceImpl personServiceImpl;

    @Override
    public List<Address> getAll(){
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
                        .owner(getOwnerById(addressDto.getPersonId()))
                        .build()
        );

        return AddressResponse.builder()
                .street(addressDto.getStreet())
                .number(addressDto.getNumber())
                .neighbourhood(addressDto.getNeighbourhood())
                .city(addressDto.getCity())
                .state(addressDto.getState())
                .zipCode(addressDto.getZipCode())
                .owner(getOwnerById(addressDto.getPersonId()))
                .build();
    }

    @Override
    public AddressResponse updateAddress(Long addressId, AddressRequest addressDto){
        validateUpdateAddress(addressId);

        addressRepository.save(
                Address.builder()
                        .street(addressDto.getStreet())
                        .number(addressDto.getNumber())
                        .neighbourhood(addressDto.getNeighbourhood())
                        .city(addressDto.getCity())
                        .state(addressDto.getState())
                        .zipCode(addressDto.getZipCode())
                        .owner(getOwnerById(addressDto.getPersonId()))
                        .build()
        );

        return AddressResponse.builder()
                .street(addressDto.getStreet())
                .number(addressDto.getNumber())
                .neighbourhood(addressDto.getNeighbourhood())
                .city(addressDto.getCity())
                .state(addressDto.getState())
                .zipCode(addressDto.getZipCode())
                .owner(getOwnerById(addressDto.getPersonId()))
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

    public Person getOwnerById(Long ownerId){
        return personServiceImpl.getPersonById(ownerId).get();
    }

}
