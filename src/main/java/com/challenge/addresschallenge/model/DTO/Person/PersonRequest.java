package com.challenge.addresschallenge.model.DTO.Person;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Data
@Value
@Builder
public class PersonRequest {

    String name;
    LocalDate dateOfBirth;
    String cpf;
    List<Long> addressesId;
}
