package com.challenge.addresschallenge.model.DTO.Address;

import com.challenge.addresschallenge.model.Person;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@Value
@Builder
public class AddressResponse {
    String street;
    String number;
    String neighbourhood;
    String city;
    String state;
    String zipCode;
    Person owner;
}
