package com.challenge.addresschallenge.Unit;

import static org.junit.Assert.assertEquals;
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

import com.challenge.addresschallenge.controller.Impl.AddressControllerImpl;
import com.challenge.addresschallenge.model.DTO.Address.AddressRequest;
import com.challenge.addresschallenge.model.DTO.Address.AddressResponse;
import com.challenge.addresschallenge.repository.AddressRepository;
import com.challenge.addresschallenge.service.impl.AddressServiceImpl;

@ExtendWith(MockitoExtension.class)
public class AddressControllerTest {
    
    @InjectMocks
    AddressControllerImpl addressController;
    
    @Mock
    private AddressServiceImpl addressService;

    @Mock
    AddressRepository addressRepository;

    @Test
    void testGetAll() {
        when(addressService.getAddresses()).thenReturn(Collections.emptyList());

        ResponseEntity<Object> response = addressController.getAll();

        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertEquals(Collections.emptyList(), response.getBody());
    }

    @Test
    void testCreateNewAddress(){
        AddressRequest dto = AddressRequest.builder().number("222").build();

        AddressResponse address = AddressResponse.builder().number(dto.getNumber()).build();

        when(addressService.createNewAddress(dto)).thenReturn(address);

        ResponseEntity<Object> response = addressController.createNewAddress(dto);

        assertEquals(HttpStatusCode.valueOf(201), response.getStatusCode());
    }

    @Test
    void testUpdateAddress(){
        AddressRequest dto = AddressRequest.builder().number("222").build();

        AddressResponse address = AddressResponse.builder().number("222").build();

        when(addressService.updateAddress((long)1, dto)).thenReturn(address);

        ResponseEntity<Object> response = addressController.updateAddress((long)1, dto);

        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
    }

    @Test
    void deleteAddress_ShouldReturnOkResponse() {
        doNothing().when(addressService).deleteAddress((long)1);

        ResponseEntity<Object> response = addressController.deleteAddress((long)1);

        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertEquals("Address deleted.", response.getBody());
    }
}