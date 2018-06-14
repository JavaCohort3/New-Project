package com.javacohort3.personapi.domain;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Person {
    @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    private ArrayList<String> hobbies = new ArrayList<>();
    
    public Person() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public ArrayList<String> getHobbies() { return hobbies; }
    public void setHobbies(ArrayList<String> hobbies) { this.hobbies = hobbies; }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", hobbies=" + hobbies +
                '}';
    }
}
