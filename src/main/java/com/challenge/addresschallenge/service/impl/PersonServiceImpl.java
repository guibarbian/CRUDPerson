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

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final AddressServiceImpl addressService;

    @Override
    public List<Person> getPeople(){
        return personRepository.findAll();
    }

    @Override
    public PersonResponse createNewPerson(PersonRequest personDto){
        validateCreatePersonDto(personDto);

        personRepository.save(
            Person.builder()
                    .name(personDto.getName())
                    .dateOfBirth(personDto.getDateOfBirth())
                    .cpf(personDto.getCpf())
                    .addresses(addressService.getAddressesById(personDto.getAddressesId()))
                    .favoriteAdress(null)
                    .build()
        );

        return PersonResponse.builder()
                                .name(personDto.getName())
                                .dateOfBirth(personDto.getDateOfBirth())
                                .cpf(personDto.getCpf())
                                .addresses(addressService.getAddressesById(personDto.getAddressesId()))
                                .favoriteAddress(null)
                                .build();

    }

    @Override
    public PersonResponse updatePerson(Long personId, PersonRequest personDto){
        validateUpdatePersonDto(personId, personDto);

        if(!personDto.getAddressesId().contains(personDto.getFavoriteAddressId()) && personDto.getFavoriteAddressId() != null){
            throw new BadRequestException("This address is not in person's list");
        }
        
        personRepository.save(
                Person.builder()
                        .id(personId)
                        .name(personDto.getName())
                        .dateOfBirth(personDto.getDateOfBirth())
                        .cpf(personDto.getCpf())
                        .addresses(addressService.getAddressesById(personDto.getAddressesId()))
                        .favoriteAdress(personDto.getFavoriteAddressId() == null ? null : addressService.getAddressById(personDto.getFavoriteAddressId()))
                        .build());

        return PersonResponse.builder()
                .name(personDto.getName())
                .dateOfBirth(personDto.getDateOfBirth())
                .cpf(personDto.getCpf())
                .addresses(addressService.getAddressesById(personDto.getAddressesId()))
                .favoriteAddress(personDto.getFavoriteAddressId() == null ? null : addressService.getAddressById(personDto.getFavoriteAddressId()))
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

        personDTO.getAddressesId().forEach(id -> {
            personRepository.findAll().forEach(person -> {
                if(person.getAddresses().contains(addressService.getAddressById(id))){
                    throw new BadRequestException("Address of id " + id + " alredy belongs to someone");
                }
            });
        });

    }

    public void validateUpdatePersonDto(Long personId, PersonRequest personDTO){
        if(personRepository.findById(personId).isEmpty()){
            throw new NotFoundException("Person was not found");
        }

        String personCpf = personRepository.findById(personId).get().getCpf();

        if(!personDTO.getCpf().equals(personCpf) && personRepository.findByCpf(personDTO.getCpf()).isPresent()){
            throw new BadRequestException("CPF " + personDTO.getCpf() + " is already in use.");
        }
        

    }

    public void validateDeletePerson(Long personId){
        if(personRepository.findById(personId).isEmpty()){
            throw new NotFoundException("Person was not found.");
        }
    }

    public Integer calculateBirthday(Long personId){
        if(personRepository.findById(personId).isEmpty()){
            throw new NotFoundException("Person not found.");
        }

        if(personRepository.findById(personId).get().getDateOfBirth() == null){
            throw new BadRequestException("This person do not have a date of birth");
        }
        LocalDate personBirth = personRepository.findById(personId).get().getDateOfBirth();
        LocalDate today = LocalDate.now();

        return Period.between(personBirth, today).getYears();
    }

    public Person getPersonById(Long personId){

        if(personRepository.findById(personId).isEmpty()) {
            throw new NotFoundException("Person not found.");
        }

        return personRepository.findById(personId).get();
    }

    public void validateFavoriteAddress(Long personId, Long addressId){

        if(!personRepository.findById(personId).get().getAddresses().contains(addressService.getAddressById(addressId))){
            throw new BadRequestException("The address must be in person's list.");
        }

    }
}
