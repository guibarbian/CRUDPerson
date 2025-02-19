package com.challenge.addresschallenge.model.DTO.Address;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@Value
@Builder
public class AddressRequest {

    String street;
    String number;
    String neighbourhood;
    String city;
    String state;
    String zipCode;
    Long personId;
}
