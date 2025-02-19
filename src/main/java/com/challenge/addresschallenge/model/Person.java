package com.challenge.addresschallenge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="dateOfBirth")
    private LocalDate dateOfBirth;

    @Column(name="cpf", unique = true, nullable = false)
    private String cpf;

    @OneToMany(mappedBy = "owner")
    @JsonIgnoreProperties("addresses")
    private List<Address> addresses;

}
