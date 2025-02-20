package com.challenge.addresschallenge.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Table(name="addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Column(name="zipcode")
    private String zipCode;
}
