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
            log.info("'GOT' one: " + personService.getPerson(id));
            return new ResponseEntity<>(person, HttpStatus.OK);
        } else {
            log.info("Could Not Find a person with this id");
            return new ResponseEntity<>(person, HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(value = "/people/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePerson(@RequestBody Person person, @PathVariable Long id) {
        Person p2 = personService.getPerson(person.getId());
        Person p = personService.updatePerson(person);

        if (p2 == p) {
            log.info("Person UPDATED : " + getPerson(id));
            return new ResponseEntity<>(person, HttpStatus.OK);
        } else {
            log.info("Person CREATED: " + getPerson(id));
            return new ResponseEntity<>(person, HttpStatus.CREATED);
        }
    }


    @RequestMapping(value = "/people/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);

        log.info("Person DELETD: " + getPerson(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
