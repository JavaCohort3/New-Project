package com.javacohort3.personapi.service;

import com.javacohort3.personapi.Person;
import com.javacohort3.personapi.exceptions.ResourceNotFoundException;
import com.javacohort3.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public void verifyPersonById(Long id) { // need the throws exception to add between long id and bracket
        if (personRepository.findOne(id) == null) throw new ResourceNotFoundException();
    }

    public void verifyPersonByEmail(String email) {
        for (Person p : personRepository.findAll()) {
            if (p.getEmail().equals(email)) {
                return;
            }
        }

        throw new ResourceNotFoundException();
    }

    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    public Person getPerson(Long id) {
        return personRepository.findOne(id);
    }

    public Person getPersonByEmail(String email) {
        for (Person p : personRepository.findAll()) {
            if (p.getEmail().equals(email)) {
                return p;
            }
        }

        return null;
    }

//    public List<String> getPersonHobbies(Long id) {
//        Person person = personRepository.findOne(id);
//        return person.getHobbies();
//    }

    public List<Person> getPersonList() {
        List<Person> people = new ArrayList<>();
        personRepository.findAll().forEach(people::add);

        return people;
    }

    public Person updatePerson(Person person) {
        return personRepository.save(person);
    }

    public void deletePerson(Long id) {
        personRepository.delete(id);
    }

}
