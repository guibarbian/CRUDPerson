package com.challenge.addresschallenge.controller;

import com.challenge.addresschallenge.model.DTO.Address.AddressRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;

@Tag(name = "Addresses", description = "Controller to managing address endpoints")
public interface AddressController {

    @Operation(
        summary = "Find all addresses",
        description = "Returns a list with all the addresses from the database",
        responses = {
            @ApiResponse(responseCode = "200", description = "Succedded to return the list")
        }
    )
    public ResponseEntity<Object> getAll();

    @Operation(
        summary = "Creates Address",
        description = "Creates a new Address in the database",
        parameters = {
            @Parameter(name = "addressDto", description = "Requistion's body to create address")
        },
        responses = {
            @ApiResponse(responseCode = "201", description = "Succedded in creating address"),
        }
    )
    public ResponseEntity<Object> createNewAddress(AddressRequest addressDto);

    @Operation(
        summary = "Updates Address",
        description = "Updates an existing Address in the database",
        parameters = {
            @Parameter(name = "addressId", description = "Id of the address to be updated"),
            @Parameter(name = "addressDto", description = "Requistion's body to update address")
        },
        responses = {
            @ApiResponse(responseCode = "200", description = "Succedded in updating address"),
            @ApiResponse(responseCode = "404", description = "Did not found the address in the database")
        }
    )
    public ResponseEntity<Object> updateAddress(Long addressId, AddressRequest addressDto);

    @Operation(
        summary = "Deletes Address",
        description = "Deletes an Address in the database",
        parameters = {
            @Parameter(name = "addressId", description = "ID of the address to be deleted")
        },
        responses = {
            @ApiResponse(responseCode = "200", description = "Succedded in deleting address"),
            @ApiResponse(responseCode = "400", description = "Tried to delete an address with owner"),
            @ApiResponse(responseCode = "404", description = "Did not found the address in the database")
        }
    )
    public ResponseEntity<Object> deleteAddress(Long addressId);
}
