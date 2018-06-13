package com.javacohort3.personapi.controller;

import com.javacohort3.personapi.service.PersonService;
import com.javacohort3.personapi.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class PersonController {
    private static final Logger log = LoggerFactory.getLogger(SpringApplication.class);
    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) { this.personService = personService; }

    // Create
    @RequestMapping(value = "/people", method = RequestMethod.POST)
    public ResponseEntity<?> createPerson(@RequestBody Person person) {
        HttpStatus status = HttpStatus.CREATED;
        Person p = personService.createPerson(person);

        HttpHeaders httpHeaders = new HttpHeaders();
        URI newUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(p.getId())
                .toUri();

        httpHeaders.setLocation(newUri);

        log.info("[POST] " + p);
        return new ResponseEntity<>(p, httpHeaders, status);
    }

    // Get One
    @RequestMapping(value = "/people/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getPerson(@PathVariable Long id) {
        HttpStatus status;
        Object response;
        Person person = personService.getPerson(id);

        personService.verifyPersonById(id);

        if (person != null) {
            // person does exist
            log.info("[GET] " + person);
            status = HttpStatus.OK;
            response = person;
        } else {
            // person does not exist
            log.info("[GET-FAILED] ID-" + id);
            status = HttpStatus.NOT_FOUND;
            response = "Person with ID " + id + " does not exist.";
        }

        return new ResponseEntity<>(response, status);
    }

    // Get One (via Email)
    @RequestMapping(value = "/people")
    public ResponseEntity<?> getPersonByEmail(@RequestParam(value="email") String query) {
        HttpStatus status;

        personService.verifyPersonByEmail(query);

        Person person = personService.getPerson()

        log.info("[GET] " + );

        return new ResponseEntity<>(response, status);
    }

    // Get One (Hobbies)
    @RequestMapping(value = "/people/{id}/hobbies", method = RequestMethod.GET)
    public ResponseEntity<?> getPersonHobbies(@PathVariable Long id) {
        HttpStatus status;
        Object response;
        Person person = personService.getPerson(id);

        personService.verifyPersonById(id);

        if (person != null) {
            // person does exist
            log.info("[GET] " + person.getHobbies());
            status = HttpStatus.OK;
            response = person.getHobbies();
        } else {
            // person does not exist
            log.info("[GET-FAILED] ID-" + id);
            status = HttpStatus.NOT_FOUND;
            response = "Person with ID " + id + " does not exist.";
        }

        return new ResponseEntity<>(response, status);
    }

    // Get All
    @RequestMapping(value = "/people", method = RequestMethod.GET)
    public ResponseEntity<?> getPersonList() {
        HttpStatus status = HttpStatus.OK;

        List<Person> people = personService.getPersonList();

       log.info("[GET] " + people);
       return new ResponseEntity<>(people, status);
    }

    // Update
    @RequestMapping(value = "/people/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePerson(@RequestBody Person person, @PathVariable Long id) {
        HttpStatus status;

        Person old_value = personService.getPerson(id);
        personService.updatePerson(person);

        if (old_value != null) {
            // person did exist prior
            log.info("[PUT-CREATED] " + person);
            status = HttpStatus.OK;
        } else {
            // person did not exist prior
            log.info("[PUT-UPDATE] " + person);
            status = HttpStatus.CREATED;
        }

        return new ResponseEntity<>(person, status);
    }

    // Delete
    @RequestMapping(value = "/people/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePerson(@PathVariable Long id) {
        HttpStatus status = HttpStatus.NO_CONTENT;
        Person person = personService.getPerson(id);

        personService.verifyPersonById(id);
        personService.deletePerson(id);
        log.info("[DELETE] Deleted ID-" + id);

        return new ResponseEntity<>(person, status);
    }
}
