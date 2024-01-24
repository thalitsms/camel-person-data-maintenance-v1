package br.com.runthebank.persondata.controller;

import br.com.runthebank.persondata.exception.NotFoundException;
import br.com.runthebank.persondata.model.UpdateResponse;
import br.com.runthebank.persondata.model.dto.PersonDto;
import br.com.runthebank.persondata.model.entity.Person;
import br.com.runthebank.persondata.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<?> createPerson(@RequestBody PersonDto personDto) {
        try {
            Person createdPerson = personService.createPerson(personDto);
            return ResponseEntity.ok("Created successfully");
        } catch (NotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        Optional<Person> person = personService.getPersonById(id);
        return person.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateResponse> updatePerson(@PathVariable Long id, @RequestBody PersonDto personDto) {
        Person updatedPerson = personService.updatePerson(id, personDto);
        if (updatedPerson != null) {
            UpdateResponse response = new UpdateResponse("Updated data successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        return ResponseEntity.ok("Customer deleted successfully");
    }
}
