package br.com.rodmds.rest_with_spring_boot_and_java_erudio.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rodmds.rest_with_spring_boot_and_java_erudio.exceptions.ResourceNotFoundException;
import br.com.rodmds.rest_with_spring_boot_and_java_erudio.model.Person;
import br.com.rodmds.rest_with_spring_boot_and_java_erudio.repositories.PersonRepository;

@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    public List<Person> findAll() {
        logger.info("Finding all people...");
        
        return repository.findAll();
    }
    
    public Person findById(Long id) {
        logger.info("Finding someone...");

        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for " + id));
    }

    public Person createPerson(Person person) {
        logger.info("Creating a person...");

        return repository.save(person);
    }

    public Person updatePerson(Person person) {
        logger.info("Updating this person...");

        Person personToUpdate = this.findById(person.getId());
        personToUpdate.setFirstName(person.getFirstName());
        personToUpdate.setLastName(person.getLastName());
        personToUpdate.setAddress(person.getAddress());
        personToUpdate.setGender(person.getGender());
        return repository.save(personToUpdate);
    }

    public void deletePerson(Long id) {
        logger.info("Deleting this person...");

        Person personToDelete = this.findById(id);
        repository.delete(personToDelete);
    }

}
