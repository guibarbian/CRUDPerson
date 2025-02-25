package com.challenge.addresschallenge.Unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.challenge.addresschallenge.model.Address;
import com.challenge.addresschallenge.model.Person;
import com.challenge.addresschallenge.model.DTO.Person.PersonRequest;
import com.challenge.addresschallenge.repository.AddressRepository;
import com.challenge.addresschallenge.repository.PersonRepository;
import com.challenge.addresschallenge.service.impl.AddressServiceImpl;
import com.challenge.addresschallenge.service.impl.PersonServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private AddressServiceImpl addressService;

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonServiceImpl personService;

    @Test
    void shallReturnListOfPeople(){

        Person person1 = Person.builder()
                                .id((long)1).name("John").cpf("123").build();
        Person person2 = Person.builder()
                                .id((long)2).name("Peter").cpf("321").build();

        when(personRepository.findAll()).thenReturn(List.of(person1, person2));

        List<Person> people = personService.getPeople();

        assertNotNull(people);
        assertEquals("John", people.get(0).getName());
        assertEquals("Peter", people.get(1).getName());
        verify(personRepository).findAll();
    }


    @Test
    void shallThrowNotFoundPersonExceptionWhenAsksPersonsAge(){

        when(personRepository.findById((long)1)).thenReturn(Optional.empty());

        try{
            personService.getPersonAge((long)1);
            fail("Exception was not thrown");
        } catch(Exception error){
            assertEquals("Person not found.",error.getMessage());
            verify(personRepository).findById((long)1); 
        }
    }

    @Test
    void shallTrhowBadRequestExceptionWhenAskPersonsAge(){
        Person person1 = Person.builder().id((long)1).dateOfBirth(null).build();
        
        when(personRepository.findById((long)1)).thenReturn(Optional.of(person1));

        try{
            personService.getPersonAge((long)1);
            fail("Exception was not thrown");
        } catch(Exception error){
            assertEquals("This person do not have a date of birth", error.getMessage());
        }
    }

    @Test
    void shallReturnPersonsAge(){
        Person person1 = Person.builder().id((long)1).dateOfBirth(LocalDate.of(2000, 1, 1)).build();

        when(personRepository.findById((long)1)).thenReturn(Optional.of(person1));

        int personAge = personService.getPersonAge((long)1);
        int varAge = Period.between(person1.getDateOfBirth(), LocalDate.now()).getYears();

        assertEquals(personAge, varAge);
    }

    @Test
    void shallThrowBadRequestWhenNameIsNull(){
        PersonRequest dto = PersonRequest.builder().name(null).build();

        try{
            personService.createNewPerson(dto);
            fail("Exception was not thrown");
        } catch(Exception error){
            assertEquals("Name must be filled.", error.getMessage());
        }
    }

    @Test
    void shallThrowBadRequestWhenCpfIsNull(){
        PersonRequest dto = PersonRequest.builder().name("Peter").cpf(null).build();

        try{
            personService.createNewPerson(dto);
            fail("Exception was not thrown");
        } catch(Exception error){
            assertEquals("CPF must be filled.", error.getMessage());
        }
    }

    @Test
    void shallThrowBadRequestWhenCpfIsAlredyInDb(){
        Person person1 = Person.builder().cpf("123").build();

        PersonRequest dto = PersonRequest.builder().name("Peter").cpf("123").build();

        when(personRepository.findByCpf(dto.getCpf())).thenReturn(Optional.of(person1));

        try{
            personService.createNewPerson(dto);
            fail("Exception was not thrown");
        } catch(Exception error){
            assertEquals("CPF is already in use.", error.getMessage());
        }
    }

    @Test
    void shallThrowBadRequestWhenTriesToCreateAPersonWithAnAlreadyUsedAddress(){

        Address address = Address.builder().id((long)1).build();

        Person person1 = Person.builder().id((long)1).addresses(List.of(address)).build();

        PersonRequest dto = PersonRequest.builder().name("Peter").cpf("123").addressesId(List.of((long)1)).build();

        when(personRepository.findAll()).thenReturn(List.of(person1));
        when(addressRepository.findById((long)1)).thenReturn(Optional.of(address));

        try{
            personService.createNewPerson(dto);
            fail("Exception not thrown");
        } catch(Exception error){
            assertEquals("Address of id 1 alredy belongs to someone", error.getMessage());
        }
    }


    
}
