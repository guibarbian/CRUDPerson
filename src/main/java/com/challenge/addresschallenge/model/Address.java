package com.challenge.addresschallenge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Data
@Value
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Table(name="addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name="street")
    String street;

    @Column(name="number")
    String number;

    @Column(name="neighbourhood")
    String neighbourhood;

    @Column(name="city")
    String city;

    @Column(name="state")
    String state;

    @Column(name="zipcode")
    String zipCode;

    @JsonIgnoreProperties("owner")
    @JoinColumn(name = "person_address")
    @ManyToOne
    Person owner;

}
