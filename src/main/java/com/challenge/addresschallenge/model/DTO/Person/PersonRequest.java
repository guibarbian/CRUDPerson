package com.challenge.addresschallenge.model.DTO.Person;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class PersonRequest {

    private  String name;
    private LocalDate dateOfBirth;
    private String cpf;
    private List<Long> addressesId;
    private Long favoriteAddressId;
}
