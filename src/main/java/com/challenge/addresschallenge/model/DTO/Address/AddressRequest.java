package com.challenge.addresschallenge.model.DTO.Address;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressRequest {

    private String street;
    private String number;
    private String neighbourhood;
    private String city;
    private String state;
    private String zipCode;
}
