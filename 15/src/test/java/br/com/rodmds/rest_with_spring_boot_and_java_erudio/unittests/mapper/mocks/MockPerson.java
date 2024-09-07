package br.com.rodmds.rest_with_spring_boot_and_java_erudio.unittests.mapper.mocks;

import java.util.ArrayList;
import java.util.List;

import br.com.rodmds.rest_with_spring_boot_and_java_erudio.data.vo.v1.PersonVO;
import br.com.rodmds.rest_with_spring_boot_and_java_erudio.model.Person;

public class MockPerson {

    public Person mockEntity() {
        return mockEntity(0);
    }

    public PersonVO mockVO() {
        return mockVO(0);
    }

    public List<Person> mockEntityList() {
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            people.add(mockEntity(i));
        }
        return people;
    }

    public List<PersonVO> mockVOList() {
        List<PersonVO> peopleVO = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            peopleVO.add(mockVO(i));
        }
        return peopleVO;
    }

    public Person mockEntity(Integer number) {
        Person personEntity = new Person();
        personEntity.setId(number.longValue());
        personEntity.setFirstName("First Name of " + number);
        personEntity.setLastName("Last Name of " + number);
        personEntity.setAddress("Address of " + number);
        personEntity.setGender(((number % 2) == 0) ? "Male" : "Female");
        return personEntity;
    }

    public PersonVO mockVO(Integer number) {
        PersonVO personVO = new PersonVO();
        personVO.setKey(number.longValue());
        personVO.setFirstName("First Name of " + number);
        personVO.setLastName("Last Name of " + number);
        personVO.setAddress("Address of " + number);
        personVO.setGender(((number % 2) == 0) ? "Male" : "Female");
        return personVO;
    }

}
