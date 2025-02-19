package com.challenge.addresschallenge.controller;

import com.challenge.addresschallenge.model.DTO.Person.PersonRequest;
import org.springframework.http.ResponseEntity;

public interface PersonController {

    ResponseEntity<Object> getAllPeople();

    ResponseEntity<Object> createNewPerson(PersonRequest personDto);

    ResponseEntity<Object> updatePerson(Long personId, PersonRequest personDto);

    ResponseEntity<Object> deletePerson(Long personId);

    ResponseEntity<Object> getPersonAge(Long personId);
}
