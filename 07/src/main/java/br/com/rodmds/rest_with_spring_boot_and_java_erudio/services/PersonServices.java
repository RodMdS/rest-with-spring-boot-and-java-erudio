package br.com.rodmds.rest_with_spring_boot_and_java_erudio.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.rodmds.rest_with_spring_boot_and_java_erudio.model.Person;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public Person findById(String id) {
        logger.info("Finding someone...");
        Person person = mockPerson(0);
        return person;
    }

    public List<Person> findAll() {
        logger.info("Finding all people...");

        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            Person person = mockPerson(i);
            persons.add(person);
        }
        return persons;
    }

    public Person createPerson(Person person) {
        logger.info("Creating a person...");
        return person;
    }

    public Person updatePerson(Person person) {
        logger.info("Updating this person...");
        return person;
    }

    public void deletePerson(String id) {
        logger.info("Deleting this person...");
    }

    private Person mockPerson(int i) {
        Person mockPerson = new Person();
        mockPerson.setId(counter.incrementAndGet());
        mockPerson.setFirstName("First Name " + i);
        mockPerson.setLastName("Last Name " + i);
        mockPerson.setAddress("Some address " + i);
        mockPerson.setGender((i % 2 == 0) ? "Male" : "Female");
        return mockPerson;
    }
}
