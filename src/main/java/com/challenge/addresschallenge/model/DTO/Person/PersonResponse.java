package com.challenge.addresschallenge.model.DTO.Person;

import com.challenge.addresschallenge.model.Address;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Data
@Value
@Builder
public class PersonResponse {

    private String name;
    private LocalDate dateOfBirth;
    private String cpf;
    private List<Address> addresses;
}
