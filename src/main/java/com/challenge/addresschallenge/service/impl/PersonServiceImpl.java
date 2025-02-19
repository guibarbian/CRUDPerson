package com.challenge.addresschallenge.service.impl;

import com.challenge.addresschallenge.exception.BadRequestException;
import com.challenge.addresschallenge.exception.NotFoundException;
import com.challenge.addresschallenge.model.DTO.Person.PersonRequest;
import com.challenge.addresschallenge.model.DTO.Person.PersonResponse;
import com.challenge.addresschallenge.model.Person;
import com.challenge.addresschallenge.repository.PersonRepository;
import com.challenge.addresschallenge.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    // private final AddressServiceImpl addressService;

    @Override
    public List<Person> getPeople(){
        return personRepository.findAll();
    }

    @Override
    public PersonResponse createNewPerson(PersonRequest newPerson){
        validateCreatePersonDto(newPerson);

        personRepository.save(
                Person.builder()
                        .name(newPerson.getName())
                        .dateOfBirth(newPerson.getDateOfBirth())
                        .cpf(newPerson.getCpf())
                        .addresses(List.of())
                        .build()
        );

        return PersonResponse.builder()
                .name(newPerson.getName())
                .dateOfBirth(newPerson.getDateOfBirth())
                .cpf(newPerson.getCpf())
                .addresses(List.of())
                .build();

    }

    @Override
    public PersonResponse updatePerson(Long personId, PersonRequest personDto){
        validateUpdatePersonDto(personId, personDto);
        
        personRepository.save(
                Person.builder()
                        .name(personDto.getName())
                        .dateOfBirth(personDto.getDateOfBirth())
                        .cpf(personDto.getCpf())
                        .addresses(List.of())
                        .build());

        return PersonResponse.builder()
                .name(personDto.getName())
                .dateOfBirth(personDto.getDateOfBirth())
                .cpf(personDto.getCpf())
                .addresses(List.of())
                .build();
    }

    public void deletePerson(Long personId){
        validateDeletePerson(personId);

        personRepository.deleteById(personId);
    }

    public Integer getPersonAge(Long personId){
        validateDeletePerson(personId);

        return calculateBirthday(personId);
    }

    // FUNCTIONS

    public void validateCreatePersonDto(PersonRequest personDTO){
        if(personDTO.getName().isEmpty()){
            throw new BadRequestException("Name must be filled.");
        }

        if(personDTO.getCpf().isEmpty()){
            throw new BadRequestException("CPF must be filled.");
        }

        if(personRepository.findByCpf(personDTO.getCpf()).isPresent()){
            throw new BadRequestException("CPF already in use.");
        }
    }

    public void validateUpdatePersonDto(Long personId, PersonRequest personDTO){
        if(personRepository.findById(personId).isEmpty()){
            throw new NotFoundException("Person not found");
        }

        if(personRepository.findByCpf(personDTO.getCpf()).isPresent()){
            throw new BadRequestException("CPF already in use.");
        }
    }

    public void validateDeletePerson(Long personId){
        if(personRepository.findById(personId).isEmpty()){
            throw new NotFoundException("Person not found.");
        }
    }

    public Integer calculateBirthday(Long personId){
        if(personRepository.findById(personId).isEmpty()){
            throw new NotFoundException("Person not found.");
        }
        LocalDate personBirth = personRepository.findById(personId).get().getDateOfBirth();
        LocalDate today = LocalDate.now();

        return Period.between(personBirth, today).getYears();
    }

    public Optional<Person> getPersonById(Long personId){

        if(personRepository.findById(personId).isEmpty()) {
            throw new NotFoundException("Person not found.");
        }

        return personRepository.findById(personId);
    }
}
