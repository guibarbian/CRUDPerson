package com.challenge.addresschallenge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Data
@Value
@Entity
@Builder
@Table(name="addresses")
public class Address {

    @Id
    @Column(name="id")
    private Long id;

    @Column(name="street")
    private String street;

    @Column(name="number")
    private String number;

    @Column(name="neighbourhood")
    private String neighbourhood;

    @Column(name="city")
    private String city;

    @Column(name="state")
    private String state;

    @Column(name="zipCode")
    private String zipCode;

    @JsonIgnoreProperties("owner")
    @JoinColumn(name = "person_addresses")
    @ManyToOne
    private Person owner;

    public Address(Long id, String street, String number, String neighbourhood, String city, String state, String zipCode, Person owner){

        this.id = id;
        this.street = street;
        this.number = number;
        this.neighbourhood = neighbourhood;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.owner = owner;
    }

    public Address(){
        this.id = null;
        this.street = null;
        this.number = null;
        this.neighbourhood = null;
        this.city = null;
        this.state = null;
        this.zipCode = null;
        this.owner = null;
    }

}
