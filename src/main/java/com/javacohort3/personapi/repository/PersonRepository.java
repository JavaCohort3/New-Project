package com.javacohort3.personapi.repository;

import com.javacohort3.personapi.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
public interface PersonRepository extends JpaRepository<Person, Long> {}
