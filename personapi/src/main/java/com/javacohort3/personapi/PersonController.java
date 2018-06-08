package com.javacohort3.personapi;

import com.rose.demobuildingrest.domain.Person;
import com.rose.demobuildingrest.repository.PersonRepository;
import com.rose.demobuildingrest.service.PeronService;
import exception.ResourceNotFoundException;
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

    //    Person p =
    private PersonRepository personRepository;
    private static final Logger log = LoggerFactory.getLogger(SpringApplication.class);
    @Autowired
    private PeronService peronService;

    protected void verifyID(Long id) throws ResourceNotFoundException {
        Person person = personRepository.findById(id).orElse(null);
        if (person == null) {
            throw new ResourceNotFoundException("Person with id " + id + " not found");
        }
    }

    @RequestMapping("/people")
    public List<Person> getPersonList() {
        return peronService.getPersonList();
    }

    @RequestMapping(value = "/people", method = RequestMethod.POST)
    public ResponseEntity<?> createPerson(@RequestBody Person person) {
        Person p = peronService.createPerson(person);
        return new ResponseEntity<>(p,HttpStatus.OK);
    }

    @RequestMapping(value = "/people/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePerson(@PathVariable Long id) {
        verifyID(id);
        peronService.deletePerson(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/people/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getPerson(@PathVariable Long id) throws ResourceNotFoundException {
        verifyID(id);
        Person person = peronService.getPerson(id);


        if (person.getId() == id) {
            return new ResponseEntity<>(person, HttpStatus.OK);
        }
//            if (person.getId() != id) {
//                throw new ResourceNotFoundException("Person not found");
//            }
        else {
            return new ResponseEntity<>(person, HttpStatus.NOT_FOUND);
        }

    }



    @RequestMapping(value = "/people/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePerson(@RequestBody Person person) {
        Person p2 = peronService.getPerson(person.getId());
        Person p = peronService.updatePerson(person);


        if (p2 == p) {
            return new ResponseEntity<>(person, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(person, HttpStatus.CREATED);

        }
//        log.info();
    }
}
