package br.com.rodmds.rest_with_spring_boot_and_java_erudio.unittests.mapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.rodmds.rest_with_spring_boot_and_java_erudio.data.vo.v1.PersonVO;
import br.com.rodmds.rest_with_spring_boot_and_java_erudio.mapper.DozerMapper;
import br.com.rodmds.rest_with_spring_boot_and_java_erudio.model.Person;
import br.com.rodmds.rest_with_spring_boot_and_java_erudio.unittests.mapper.mocks.MockPerson;

public class DozerConverterTests {

    MockPerson inputObject;

    @BeforeEach
    public void setup() {
        inputObject = new MockPerson();
    }

    @Test
    public void parseEntityToVOTest() {
        PersonVO output = DozerMapper.parseObject(inputObject.mockEntity(), PersonVO.class);
        assertEquals(Long.valueOf(0L), output.getId());
        assertEquals("First Name of 0", output.getFirstName());
        assertEquals("Last Name of 0", output.getLastName());
        assertEquals("Address of 0", output.getAddress());
        assertEquals("Male", output.getGender());
    }

    @Test
    public void parseEntityListToVOListTest() {
        List<PersonVO> outputList = DozerMapper.parseListObjects(inputObject.mockEntityList(), PersonVO.class);

        PersonVO outputZero = outputList.get(0);
        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("First Name of 0", outputZero.getFirstName());
        assertEquals("Last Name of 0", outputZero.getLastName());
        assertEquals("Address of 0", outputZero.getAddress());
        assertEquals("Male", outputZero.getGender());

        PersonVO outputSeven = outputList.get(7);
        assertEquals(Long.valueOf(7L), outputSeven.getId());
        assertEquals("First Name of 7", outputSeven.getFirstName());
        assertEquals("Last Name of 7", outputSeven.getLastName());
        assertEquals("Address of 7", outputSeven.getAddress());
        assertEquals("Female", outputSeven.getGender());

        PersonVO outputTwelve = outputList.get(12);
        assertEquals(Long.valueOf(12L), outputTwelve.getId());
        assertEquals("First Name of 12", outputTwelve.getFirstName());
        assertEquals("Last Name of 12", outputTwelve.getLastName());
        assertEquals("Address of 12", outputTwelve.getAddress());
        assertEquals("Male", outputTwelve.getGender());
    }

    @Test
    public void parseVoToEntityTest() {
        Person output = DozerMapper.parseObject(inputObject.mockVO(), Person.class);
        assertEquals(Long.valueOf(0L), output.getId());
        assertEquals("First Name of 0", output.getFirstName());
        assertEquals("Last Name of 0", output.getLastName());
        assertEquals("Address of 0", output.getAddress());
        assertEquals("Male", output.getGender());
    }

    @Test
    public void parseVOListToEntityListTest() {
        List<Person> outputList = DozerMapper.parseListObjects(inputObject.mockVOList(), Person.class);

        Person outputZero = outputList.get(0);
        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("First Name of 0", outputZero.getFirstName());
        assertEquals("Last Name of 0", outputZero.getLastName());
        assertEquals("Address of 0", outputZero.getAddress());
        assertEquals("Male", outputZero.getGender());

        Person outputSeven = outputList.get(7);
        assertEquals(Long.valueOf(7L), outputSeven.getId());
        assertEquals("First Name of 7", outputSeven.getFirstName());
        assertEquals("Last Name of 7", outputSeven.getLastName());
        assertEquals("Address of 7", outputSeven.getAddress());
        assertEquals("Female", outputSeven.getGender());

        Person outputTwelve = outputList.get(12);
        assertEquals(Long.valueOf(12L), outputTwelve.getId());
        assertEquals("First Name of 12", outputTwelve.getFirstName());
        assertEquals("Last Name of 12", outputTwelve.getLastName());
        assertEquals("Address of 12", outputTwelve.getAddress());
        assertEquals("Male", outputTwelve.getGender());
    }

}
