package com.challenge.addresschallenge.service;

import com.challenge.addresschallenge.model.DTO.Person.PersonRequest;
import com.challenge.addresschallenge.model.DTO.Person.PersonResponse;
import com.challenge.addresschallenge.model.Person;

import java.util.List;

public interface PersonService {

    List<Person> getPeople();

    PersonResponse createNewPerson(PersonRequest newPerson);

    PersonResponse updatePerson(Long personId, PersonRequest updatePerson);

    void deletePerson(Long personId);

    Integer getPersonAge(Long personId);
}
