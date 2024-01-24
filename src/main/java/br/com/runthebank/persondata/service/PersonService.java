package br.com.runthebank.persondata.service;

import br.com.runthebank.persondata.exception.NotFoundException;
import br.com.runthebank.persondata.model.dto.PersonDto;
import br.com.runthebank.persondata.model.entity.Person;
import br.com.runthebank.persondata.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person createPerson(PersonDto personDto) {

        String documentNumber = personDto.getDocumentNumber();
        if (personRepository.existsByDocumentNumber(documentNumber)) {
            throw new NotFoundException("Customer already exists");
        }

        Person person = new Person();
        return getPerson(personDto, person);
    }

    private Person getPerson(PersonDto personDto, Person person) {
        person.setFirstName(personDto.getFirstName());
        person.setSurName(personDto.getSurName());
        person.setLastName(personDto.getLastName());
        person.setDocumentNumber(personDto.getDocumentNumber());
        person.setDocumentType(personDto.getDocumentType());
        person.setGender(personDto.getGender());
        person.setDateOfBirth(personDto.getDateOfBirth());
        person.setMothersName(personDto.getMothersName());
        person.setFathersName(personDto.getFathersName());
        person.setStateOfBirth(personDto.getStateOfBirth());
        person.setCityOfBirth(personDto.getCityOfBirth());
        person.setCountryOfBirth(personDto.getCountryOfBirth());
        person.setAddress(personDto.getAddress());
        person.setEmail(personDto.getEmail());
        person.setPhone(personDto.getPhone());
        person.setPersonalStatus(personDto.getPersonalStatus());
        person.setPoliticalExposedPersonIndicator(personDto.getPoliticalExposedPersonIndicator());
        person.setPassword(personDto.getPassword());
        return personRepository.save(person);
    }

    public Optional<Person> getPersonById(Long id) {
        Optional<Person> person = personRepository.findById(id);
        person.ifPresent(value -> value.setPassword("********"));
        return person;
    }
    public Person updatePerson(Long id, PersonDto personDto) {
        Optional<Person> existingPerson = personRepository.findById(id);
        if (existingPerson.isPresent()) {
            Person updatedPerson = existingPerson.get();
            return getPerson(personDto, updatedPerson);
        } else {
            throw new IllegalArgumentException("Client not found");
        }
    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }
}
