package com.challenge.addresschallenge.Unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import com.challenge.addresschallenge.controller.Impl.PersonControllerImpl;
import com.challenge.addresschallenge.model.DTO.Person.PersonRequest;
import com.challenge.addresschallenge.model.DTO.Person.PersonResponse;
import com.challenge.addresschallenge.service.impl.PersonServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PersonControllerTest {

    @Mock
    private PersonServiceImpl personService;

    @InjectMocks
    private PersonControllerImpl personController;

    @Test
    void testGetAll() {
        when(personService.getPeople()).thenReturn(Collections.emptyList());

        ResponseEntity<Object> response = personController.getAllPeople();

        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertEquals(Collections.emptyList(), response.getBody());
    }

    @Test
    void testGetPersonAge() {
        when(personService.getPersonAge((long)1)).thenReturn(30);

        ResponseEntity<Object> response = personController.getPersonAge((long)1);

        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertEquals(30, response.getBody());
    }

    @Test
    void testCreatePerson() {
        PersonRequest dto = PersonRequest.builder().build();
        PersonResponse person = PersonResponse.builder().build();

        when(personService.createNewPerson(dto)).thenReturn(person);

        ResponseEntity<Object> response = personController.createNewPerson(dto);

        assertEquals(HttpStatusCode.valueOf(201), response.getStatusCode());
        assertEquals(person, response.getBody());
    }

    @Test
    void updatePerson_ShouldReturnOkResponse() {
        PersonRequest dto = PersonRequest.builder().build();
        PersonResponse person = PersonResponse.builder().build();
        when(personService.updatePerson((long)1, dto)).thenReturn(person);

        ResponseEntity<Object> response = personController.updatePerson((long)1, dto);

        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertEquals(person, response.getBody());
    }

    @Test
    void deletePerson_ShouldReturnOkResponse() {
        doNothing().when(personService).deletePerson((long)1);

        ResponseEntity<Object> response = personController.deletePerson((long)1);

        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertEquals("Person deleted", response.getBody());
    }
    
}
