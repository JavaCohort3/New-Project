package com.javacohort3.personapi;

import com.javacohort3.personapi.controller.PersonController;
import com.javacohort3.personapi.service.PersonService;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootConfiguration
@WebAppConfiguration
public class PersonControllerTest {

    @Mock
    private PersonService personService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetPersonList(){
        PersonController controller = new PersonController(new PersonService());
        ReflectionTestUtils.setField(controller,"personService",personService);
        when(personService.getPersonList()).thenReturn(new ArrayList<>());
        ResponseEntity<?> personList = controller.getPersonList();
        verify(personService,times(1)).getPersonList();
        Assert.assertEquals(HttpStatus.OK,personList.getStatusCode());
        Assert.assertEquals(1, Lists.newArrayList(personList.getBody()).size());
    }

    @Test
    public void updatePerson(){
        PersonController controller = new PersonController(new PersonService());
        Person person = new Person();
        ReflectionTestUtils.setField(controller,"personService",personService);
        when(personService.updatePerson(person)).thenReturn(person);
        ResponseEntity<?> updatePerson = controller.updatePerson(person, person.getId());
        verify(personService,times(1)).updatePerson(person);
        Assert.assertEquals(HttpStatus.CREATED,updatePerson.getStatusCode());

    }

    @Test
    public void createPerson(){
        PersonController controller = new PersonController(new PersonService());
        Person person = new Person();
        ReflectionTestUtils.setField(controller,"personService",personService);
        when(personService.createPerson(person)).thenReturn(new Person());
        ResponseEntity<?> createPerson = controller.createPerson(person);
        verify(personService,times(1)).createPerson(person);
        Assert.assertEquals(HttpStatus.CREATED,createPerson.getStatusCode());
    }


    @Test
    public void getPerson(){
        PersonController controller = new PersonController(new PersonService());
        Person person = new Person();
        ReflectionTestUtils.setField(controller,"personService",personService);
        when(personService.getPerson(person.getId())).thenReturn(person);
        ResponseEntity<?> getPerson = controller.getPerson(person.getId());
        verify(personService,times(1)).getPerson(person.getId());
        Assert.assertEquals(HttpStatus.OK,getPerson.getStatusCode());
    }

//    @Test
//    public void deletePerson(){
//        PersonController controller = new PersonController(new PersonService());
//        Person person = new Person();
//        ReflectionTestUtils.setField(controller,"personService",personService);
//        when(personService.deletePerson(person.getId())).thenReturn(new ArrayList<>());
//        ResponseEntity<?> deletePerson = controller.deletePerson(person.getId());
//        verify(personService,times(1)).deletePerson(person.getId());
//        Assert.assertEquals(HttpStatus.NO_CONTENT,deletePerson.getStatusCode());
//    }

}
