package com.javacohort3.personapi.repository;

import com.javacohort3.personapi.domain.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {}