package com.javacohort3.personapi.service;

import com.javacohort3.personapi.domain.Person;
import com.javacohort3.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;


    public Person verifyPersonById(Long id) { // need the throws exception to add between long id and bracket
        return personRepository.findById(id).orElse(null);
    }

    public Person verifyPersonByEmail(String email) { // need the throws exception to add between long id and bracket
        for (Person p : personRepository.findAll()) {
            if (p.getEmail().equals(email)) {
                return p;
            }
        }

        return null;
    }


    public List<Person> getPersonList() {
        List<Person> people = new ArrayList<>();
        personRepository.findAll().forEach(people::add);

        return people;
    }


    public Person createPerson(Person person) {
        return personRepository.save(person);
    }


    public Person getPerson(Long id) {
        return personRepository.findById(id).orElse(null);
    }


    public Person updatePerson(Person person) {
        return personRepository.save(person);
    }


    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }
}
