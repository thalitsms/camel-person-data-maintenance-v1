package br.com.runthebank.persondata.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String surName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String documentNumber;

    @Column(nullable = false)
    private String documentType;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String dateOfBirth;

    @Column(nullable = false)
    private String mothersName;

    @Column(nullable = true)
    private String fathersName;

    @Column(nullable = false)
    private String stateOfBirth;

    @Column(nullable = false)
    private String cityOfBirth;

    @Column(nullable = false)
    private String countryOfBirth;

    @Column(nullable = false)
    private String personalStatus;

    @Column(nullable = false)
    private String politicalExposedPersonIndicator;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Account> accounts;

    public Person(Long id) {
    }
}
