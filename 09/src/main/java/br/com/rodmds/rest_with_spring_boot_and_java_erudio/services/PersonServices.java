package br.com.rodmds.rest_with_spring_boot_and_java_erudio.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rodmds.rest_with_spring_boot_and_java_erudio.data.vo.v1.PersonVO;
import br.com.rodmds.rest_with_spring_boot_and_java_erudio.exceptions.ResourceNotFoundException;
import br.com.rodmds.rest_with_spring_boot_and_java_erudio.mapper.DozerMapper;
import br.com.rodmds.rest_with_spring_boot_and_java_erudio.model.Person;
import br.com.rodmds.rest_with_spring_boot_and_java_erudio.repositories.PersonRepository;

@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    /*@Autowired
    PersonMapper customMapper;*/

    public List<PersonVO> findAll() {
        logger.info("Finding all people...");
        
        var entityList = repository.findAll();
        return DozerMapper.parseListObjects(entityList, PersonVO.class);
    }
    
    public PersonVO findById(Long id) {
        logger.info("Finding someone...");

        Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for " + id));
        return DozerMapper.parseObject(entity, PersonVO.class);
    }

    public PersonVO createPerson(PersonVO person) {
        logger.info("Creating a person...");

        Person entity = DozerMapper.parseObject(person, Person.class);
        PersonVO objectVO = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        return objectVO;
    }

    /*public PersonVOV2 createPersonV2(PersonVOV2 person) {
        logger.info("Creating a person...");

        Person entity = customMapper.convertVOToEntity(person);
        PersonVOV2 objectVOV2 = customMapper.convertEntityToVO(repository.save(entity));
        return objectVOV2;
    }*/

    public PersonVO updatePerson(PersonVO person) {
        logger.info("Updating this person...");

        Person entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for " + person.getId()));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        PersonVO objectVO = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        return objectVO;
    }

    public void deletePerson(Long id) {
        logger.info("Deleting this person...");

        Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for " + id));
        repository.delete(entity);
    }

}
