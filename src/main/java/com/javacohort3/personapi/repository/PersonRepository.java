package com.javacohort3.personapi.repository;

import com.javacohort3.personapi.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {}
