package com.challenge.addresschallenge.controller.Impl;

import com.challenge.addresschallenge.controller.PersonController;
import com.challenge.addresschallenge.model.DTO.Person.PersonRequest;
import com.challenge.addresschallenge.service.impl.PersonServiceImpl;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonControllerImpl implements PersonController {

    private final PersonServiceImpl personService;

    @Override
    @GetMapping
    public ResponseEntity<Object> getAllPeople(){
        return ResponseEntity.ok().body(personService.getPeople());
    }

    @Override
    @GetMapping("/{personId}")
    public ResponseEntity<Object> getPersonAge(@PathVariable Long personId){
        return ResponseEntity.ok().body(personService.getPersonAge(personId));
    }

    @Override
    @PostMapping
    public ResponseEntity<Object> createNewPerson(@RequestBody PersonRequest personDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(personService.createNewPerson(personDto));
    }

    @Override
    @PutMapping("/{personId}")
    public ResponseEntity<Object> updatePerson(@PathVariable Long personId,
                                               @RequestBody PersonRequest personDto){
        return ResponseEntity.ok().body(personService.updatePerson(personId, personDto));
    }

    @Override
    @DeleteMapping("/{personId}")
    public ResponseEntity<Object> deletePerson(@PathVariable Long personId){
        personService.deletePerson(personId);
        return ResponseEntity.ok().body("Person deleted");
    }
}
