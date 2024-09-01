package br.com.rodmds.rest_with_spring_boot_and_java_erudio.mapper.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.rodmds.rest_with_spring_boot_and_java_erudio.data.vo.v2.PersonVOV2;
import br.com.rodmds.rest_with_spring_boot_and_java_erudio.model.Person;

@Service
public class PersonMapper {

    public PersonVOV2 convertEntityToVO(Person person) {
        PersonVOV2 vo = new PersonVOV2();
        vo.setId(person.getId());
        vo.setFirstName(person.getFirstName());
        vo.setLastName(person.getLastName());
        vo.setAddress(person.getAddress());
        vo.setGender(person.getAddress());
        vo.setBirthDay(new Date());
        return vo;
    }

    public Person convertVOToEntity(PersonVOV2 vo) {
        Person entity = new Person();
        entity.setId(vo.getId());
        entity.setFirstName(vo.getFirstName());
        entity.setLastName(vo.getLastName());
        entity.setAddress(vo.getAddress());
        entity.setGender(vo.getAddress());
        //entity.setBirthDay(new Date());
        return entity;
    }

}
