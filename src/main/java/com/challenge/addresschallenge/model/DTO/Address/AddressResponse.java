package com.challenge.addresschallenge.model.DTO.Address;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@Value
@Builder
public class AddressResponse {
    private String street;
    private String number;
    private String neighbourhood;
    private String city;
    private String state;
    private String zipCode;
}
