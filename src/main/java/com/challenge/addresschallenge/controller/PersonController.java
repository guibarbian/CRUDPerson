package com.challenge.addresschallenge.controller;

import com.challenge.addresschallenge.model.DTO.Person.PersonRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;

@Tag(name = "People", description = "Controller to manage the person endpoints")
public interface PersonController {

    @Operation(
        summary = "Get people",
        description = "Lists all the people in the database ",
        responses = {
            @ApiResponse(responseCode = "200", description = "Succedded in listing all the people"),
        }
    )
    ResponseEntity<Object> getAllPeople();

    @Operation(
        summary = "Creates Person",
        description = "Creates a new Person in the database",
        parameters = {
            @Parameter(name = "personDto", description = "Requistion's body to create person")
        },
        responses = {
            @ApiResponse(responseCode = "201", description = "Succedded in creating person"),
            @ApiResponse(responseCode = "400", description = "Tried to create a person without name or CPF or with an CPF already in use, or tried to create a person with an address already in use"),
            @ApiResponse(responseCode = "404", description = "Did not found the addresses for the person")
        }
    )
    ResponseEntity<Object> createNewPerson(PersonRequest personDto);

    @Operation(
        summary = "Updates Person",
        description = "Updates an existing Person in the database",
        parameters = {
            @Parameter(name = "personId", description = "ID of the person to be updated"),
            @Parameter(name = "personDto", description = "Requistion's body to update person")
        },
        responses = {
            @ApiResponse(responseCode = "200", description = "Succedded in updating person"),
            @ApiResponse(responseCode = "400", description = "Tried to update a person without name or CPF or with an CPF already in use, or tried to update a person with an address already in use"),
            @ApiResponse(responseCode = "404", description = "Did not found the person or the addresses for the person")
        }
    )
    ResponseEntity<Object> updatePerson(Long personId, PersonRequest personDto);

    @Operation(
        summary = "Delete Person",
        description = "Deletes an existing Person in the database",
        parameters = {
            @Parameter(name = "personId", description = "ID of the person to be deleted")
        },
        responses = {
            @ApiResponse(responseCode = "200", description = "Succedded in deleting person"),
            @ApiResponse(responseCode = "404", description = "Did not found the person")
        }
    )
    ResponseEntity<Object> deletePerson(Long personId);

    @Operation(
        summary = "Get persons age",
        description = "Shows the age of the person",
        parameters = {
            @Parameter(name = "personId", description = "ID of the person to know her age")
        },
        responses = {
            @ApiResponse(responseCode = "200", description = "Succedded calculating person's age"),
            @ApiResponse(responseCode = "400", description = "Tried to calculate a person's age with her not having a dateOfBirth"),
            @ApiResponse(responseCode = "404", description = "Did not found the addresses for person")
        }
    )
    ResponseEntity<Object> getPersonAge(Long personId);
}
