package com.challenge.addresschallenge.Unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.challenge.addresschallenge.model.Address;
import com.challenge.addresschallenge.model.DTO.Address.AddressRequest;
import com.challenge.addresschallenge.model.DTO.Address.AddressResponse;
import com.challenge.addresschallenge.repository.AddressRepository;
import com.challenge.addresschallenge.service.impl.AddressServiceImpl;

@ExtendWith(MockitoExtension.class)
@DisplayName("Address Service Test")
public class AddressServiceTest {
    
    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressServiceImpl addressService;

    @Test
    void shallReturnListOfAddresses() {
        
        Address address1 = Address.builder()
                                    .id((long)1).number("222").build();
        Address address2 = Address.builder()
                                    .id((long)2).number("123").build();

        List<Address> addresses = List.of(address1, address2);

        when(addressRepository.findAll()).thenReturn(addresses);

        List<Address> result = addressService.getAddresses();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("222", result.get(0).getNumber());
        assertEquals("123", result.get(1).getNumber());
        verify(addressRepository).findAll();
    }

    @Test
    void shallCreateNewPerson(){
        AddressRequest addressDto = AddressRequest.builder().number("222").build();

        Address address1 = Address.builder().number(addressDto.getNumber()).build();

        when(addressRepository.save(any(Address.class))).thenReturn(address1);

        AddressResponse response = addressService.createNewAddress(addressDto);

        assertNotNull(response);
        assertEquals("222", response.getNumber());
        verify(addressRepository).save(any(Address.class));
                        
    }

    @Test
    void shallThrowNotFound(){

        AddressRequest dto = AddressRequest.builder().number("222").build();
        
        when(addressRepository.findById((long)1)).thenReturn(Optional.empty());

        try{
            addressService.updateAddress((long)1, dto);
            fail("Exception was not thrown");
        } catch(Exception error){
            assertEquals("Address Not Found", error.getMessage());
            verify(addressRepository).findById((long)1);
        }
    }

    @Test
    void shallUpdateAddress(){

        AddressRequest dto = AddressRequest.builder().number("222").build();

        Address address = Address.builder().id((long)1).number("123").build();

        when(addressRepository.findById((long)1)).thenReturn(Optional.of(address));

        AddressResponse response = addressService.updateAddress((long)1, dto);

        assertEquals("222", response.getNumber());
        verify(addressRepository).findById((long)1);
    }

    @Test
    void shallThrowNotFoundExceptionWhenDeletes(){

        when(addressRepository.findById((long)1)).thenReturn(Optional.empty());

        try{
            addressService.deleteAddress((long)1);
            fail("Exception not thrown");
        } catch(Exception error){
            verify(addressRepository).findById((long)1);
            assertEquals("Address Not Found", error.getMessage());
        }
    }

    @Test
    void shallDeleteAddress(){

        Address address = Address.builder().id((long)1).build();

        when(addressRepository.findById((long)1)).thenReturn(Optional.of(address));

        addressService.deleteAddress((long)1);
        verify(addressRepository).findById((long)1);
        verify(addressRepository).deleteById((long)1);
    }
}
