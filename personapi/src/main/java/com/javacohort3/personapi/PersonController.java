package com.javacohort3.personapi;

import com.javacohort3.personapi.service.PersonService;
import com.javacohort3.personapi.Domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {


    private static final Logger log = LoggerFactory.getLogger(SpringApplication.class);

    @Autowired
    private PersonService personService;

    protected void verifyID(Long id) throws ResourceNotFoundException {

        if(personService.verifyPerson(id) == null) {
            throw new ResourceNotFoundException("Person with id: " +
                    id + ", is not found. Please try again");
        }
    }



    @RequestMapping("/people")
    public ResponseEntity<?> getPersonList() {
       List p = personService.getPersonList();

       log.info("GET ALL:  " + p);
       return new ResponseEntity<>(p, HttpStatus.OK);
    }


    @RequestMapping(value = "/people", method = RequestMethod.POST)
    public ResponseEntity<?> createPerson(@RequestBody Person person) {
        Person p = personService.createPerson(person);

        log.info("Person CREATED " + p);
        return new ResponseEntity<>(p,HttpStatus.OK);
    }


    @RequestMapping(value = "/people/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getPerson(@PathVariable Long id) {
        Person person = personService.getPerson(id);

        if (person.getId() == id) {
            log.info("'GETTING': " + person);
            return new ResponseEntity<>(person, HttpStatus.OK);
        } else {
            log.info("Could Not Find a person with id: " + id);
            return new ResponseEntity<>(person, HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(value = "/people/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePerson(@RequestBody Person person, @PathVariable Long id) {
        log.info("Verifying");
        verifyID(id);

        Person p2 = personService.getPerson(person.getId());
        Person p = personService.updatePerson(person);

        if (p2 == p) {
            log.info("Person UPDATED: " + p2);
            return new ResponseEntity<>(person, HttpStatus.OK);
        } else {
            log.info("Person CREATED: " + p2);
            return new ResponseEntity<>(person, HttpStatus.CREATED);
        }
    }


    @RequestMapping(value = "/people/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePerson(@PathVariable Long id) {
        log.info("Verifying");
        verifyID(id);

        personService.deletePerson(id);
        Person p = personService.getPerson(id);

        log.info("Person DELETED: " + p);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
