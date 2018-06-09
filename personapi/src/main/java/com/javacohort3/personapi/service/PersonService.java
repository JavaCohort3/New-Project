package com.javacohort3.personapi.service;



import com.javacohort3.personapi.Domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
// add import for exception package should look like:
//    import com.javacohort3.personapi.exception.*; TODO MAKE SURE:
//    the import is the folder structure we made because it wont do it automatically
//    It will take a whole other import automatically instead of the one we want do
//    the same with PersonRepository: import com.javacohort3.personapi.repo.*;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;


    public Person verifyPerson(Long id) { // need the throws exception to add between long id and bracket
        return personRepository.findOne(id);
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
        return personRepository.findOne(id);
    }


    public Person updatePerson(Person person) {
        return personRepository.save(person);
    }


    public void deletePerson(Long id) {
        personRepository.delete(id);
    }
}
