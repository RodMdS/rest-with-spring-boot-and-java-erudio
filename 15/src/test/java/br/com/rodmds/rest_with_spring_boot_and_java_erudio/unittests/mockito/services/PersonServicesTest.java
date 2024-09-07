package br.com.rodmds.rest_with_spring_boot_and_java_erudio.unittests.mockito.services;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.rodmds.rest_with_spring_boot_and_java_erudio.data.vo.v1.PersonVO;
import br.com.rodmds.rest_with_spring_boot_and_java_erudio.exceptions.RequiredObjectIsNullException;
import br.com.rodmds.rest_with_spring_boot_and_java_erudio.model.Person;
import br.com.rodmds.rest_with_spring_boot_and_java_erudio.repositories.PersonRepository;
import br.com.rodmds.rest_with_spring_boot_and_java_erudio.services.PersonServices;
import br.com.rodmds.rest_with_spring_boot_and_java_erudio.unittests.mapper.mocks.MockPerson;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class PersonServicesTest {

    MockPerson input;

    @InjectMocks
    private PersonServices service;

    @Mock
    PersonRepository repository;

    @BeforeEach
    void setUpMocks() {
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById() {
        Person entity = input.mockEntity(1);
        entity.setId(1L);
        
        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        var result = service.findById(1L);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));

        assertEquals("First Name of 1", result.getFirstName());
        assertEquals("Last Name of 1", result.getLastName());
        assertEquals("Address of 1", result.getAddress());
        assertEquals("Female", result.getGender());
    }

    @Test
    void testFindAll() {
        List<Person> listMockPeople = input.mockEntityList();
        
        when(repository.findAll()).thenReturn(listMockPeople);

        var people = service.findAll();

        assertNotNull(people);
        assertEquals(14, people.size());

        var personOne = people.get(1);
        assertNotNull(personOne);
        assertNotNull(personOne.getKey());
        assertNotNull(personOne.getLinks());
        assertTrue(personOne.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("First Name of 1", personOne.getFirstName());
        assertEquals("Last Name of 1", personOne.getLastName());
        assertEquals("Address of 1", personOne.getAddress());
        assertEquals("Female", personOne.getGender());

        var personFour = people.get(4);
        assertNotNull(personFour);
        assertNotNull(personFour.getKey());
        assertNotNull(personFour.getLinks());
        assertTrue(personFour.toString().contains("links: [</api/person/v1/4>;rel=\"self\"]"));
        assertEquals("First Name of 4", personFour.getFirstName());
        assertEquals("Last Name of 4", personFour.getLastName());
        assertEquals("Address of 4", personFour.getAddress());
        assertEquals("Male", personFour.getGender());

        var personSeven = people.get(7);
        assertNotNull(personSeven);
        assertNotNull(personSeven.getKey());
        assertNotNull(personSeven.getLinks());
        assertTrue(personSeven.toString().contains("links: [</api/person/v1/7>;rel=\"self\"]"));
        assertEquals("First Name of 7", personSeven.getFirstName());
        assertEquals("Last Name of 7", personSeven.getLastName());
        assertEquals("Address of 7", personSeven.getAddress());
        assertEquals("Female", personSeven.getGender());
    }

    @Test
    void testCreatePerson() {
        Person entity = input.mockEntity(1);

        Person persisted = entity;
        persisted.setId(1L);
        
        PersonVO vo = input.mockVO(1);
        vo.setKey(1L);

        when(repository.save(entity)).thenReturn(persisted);

        var result = service.createPerson(vo);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));

        assertEquals("First Name of 1", result.getFirstName());
        assertEquals("Last Name of 1", result.getLastName());
        assertEquals("Address of 1", result.getAddress());
        assertEquals("Female", result.getGender());
    }

    @Test
    void testCreateWithNullPerson() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.createPerson(null);
        });

        String expectedErrorMessage = "It is not allowed to persist a null object!";
        String actualErrorMessage = exception.getMessage();
        assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    @Test
    void testUpdatePerson() {
        Person entity = input.mockEntity(1);
        entity.setId(1L);

        Person persisted = entity;
        persisted.setId(1L);
        
        PersonVO vo = input.mockVO(1);
        vo.setKey(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(persisted);

        var result = service.updatePerson(vo);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));

        assertEquals("First Name of 1", result.getFirstName());
        assertEquals("Last Name of 1", result.getLastName());
        assertEquals("Address of 1", result.getAddress());
        assertEquals("Female", result.getGender());
    }

    @Test
    void testUpdateWithNullPerson() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.updatePerson(null);
        });

        String expectedErrorMessage = "It is not allowed to persist a null object!";
        String actualErrorMessage = exception.getMessage();
        assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    @Test
    void testDeletePerson() {
        Person entity = input.mockEntity(1);
        entity.setId(1L);
        
        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        service.deletePerson(1L);
    }

}
