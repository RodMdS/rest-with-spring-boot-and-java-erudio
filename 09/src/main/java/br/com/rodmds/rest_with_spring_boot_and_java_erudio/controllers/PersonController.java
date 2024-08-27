package br.com.rodmds.rest_with_spring_boot_and_java_erudio.controllers;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rodmds.rest_with_spring_boot_and_java_erudio.data.vo.v1.PersonVO;
import br.com.rodmds.rest_with_spring_boot_and_java_erudio.services.PersonServices;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonServices service;

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @GetMapping(value = "/{id}",
                produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonVO findById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonVO> findAll() {
        return service.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonVO createPersonVO(@RequestBody PersonVO person) {
        return service.createPerson(person);
    }

    /*@PostMapping(path = "/v2", 
                    consumes = MediaType.APPLICATION_JSON_VALUE, 
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonVOV2 createPersonVOV2(@RequestBody PersonVOV2 person) {
        return service.createPersonV2(person);
    }*/

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
                produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonVO updatePersonVO(@RequestBody PersonVO person) {
        return service.updatePerson(person);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletePersonVO(@PathVariable(value = "id") Long id) {
        service.deletePerson(id);
        return ResponseEntity.noContent().build();
    }

}
