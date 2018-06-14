package com.javacohort3.personapi.repository;

import com.javacohort3.personapi.domain.Person;
import com.javacohort3.personapi.exceptions.ResourceNotFoundException;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
    void verifyPersonByEmail(String email);
    void findPersonByEmail(String email);
}
