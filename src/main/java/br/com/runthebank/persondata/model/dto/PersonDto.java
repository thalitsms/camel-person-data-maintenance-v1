package br.com.runthebank.persondata.model.dto;

import lombok.Data;
@Data
public class PersonDto {

    private Long id;
    private String firstName;
    private String surName;
    private String lastName;
    private String documentNumber;
    private String documentType;
    private String gender;
    private String dateOfBirth;
    private String mothersName;
    private String fathersName;
    private String stateOfBirth;
    private String cityOfBirth;
    private String countryOfBirth;
    private String personalStatus;
    private String politicalExposedPersonIndicator;
    private String password;
    private String address;
    private String email;
    private String phone;

}

