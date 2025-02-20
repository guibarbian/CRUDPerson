package com.challenge.addresschallenge.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@Value
@Entity
@Builder
@AllArgsConstructor
@Table(name="people")
@NoArgsConstructor(force = true)
public class Person {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name="name", nullable = false)
    String name;

    @Column(name="dateOfBirth")
    LocalDate dateOfBirth;

    @Column(name="cpf", unique = true, nullable = false)
    String cpf;

    @OneToMany(cascade = CascadeType.ALL ,orphanRemoval = true)
    List<Address> addresses;

    @OneToOne
    Address favoriteAdress;

}
