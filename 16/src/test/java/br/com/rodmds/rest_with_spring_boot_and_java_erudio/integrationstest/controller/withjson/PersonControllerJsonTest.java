package br.com.rodmds.rest_with_spring_boot_and_java_erudio.integrationstest.controller.withjson;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.rodmds.rest_with_spring_boot_and_java_erudio.configs.TestConfigs;
import br.com.rodmds.rest_with_spring_boot_and_java_erudio.integrationstest.testcontainers.AbstractIntegrationTest;
import br.com.rodmds.rest_with_spring_boot_and_java_erudio.integrationstest.vo.PersonVO;
import static io.restassured.RestAssured.given;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class PersonControllerJsonTest extends AbstractIntegrationTest {

    private static RequestSpecification specification;
    private static ObjectMapper objectMapper;

    private static PersonVO person;

    @BeforeAll
    public static void setup() {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        person = new PersonVO();
    }

    private void mockPerson() {
        person.setFirstName("Person Test FN");
        person.setLastName("Person Test LN");
        person.setAddress("Person Test Address");
        person.setGender("Male");
    }

	@Test
    @Order(1)
	public void testCreatePerson() throws JsonMappingException, JsonProcessingException {
        mockPerson();

        specification = new RequestSpecBuilder()
                            .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_ERUDIO)
                            .setBasePath("api/person/v1")
                            .setPort(TestConfigs.SERVER_PORT)
                                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                            .build();

		var content = given()
                        .spec(specification)
                        .contentType(TestConfigs.CONTENT_TYPE_JSON)
                            .body(person)
                            .when()
                            .post()
						.then()
							.statusCode(200)
						.extract()
							.body()
								.asString();

        PersonVO createdPerson = objectMapper.readValue(content, PersonVO.class);
        person = createdPerson;
        
		assertNotNull(createdPerson);
        
        assertNotNull(createdPerson.getId());
        assertTrue(createdPerson.getId() > 0);
        
        assertNotNull(createdPerson.getFirstName());
        assertNotNull(createdPerson.getLastName());
        assertNotNull(createdPerson.getAddress());
        assertNotNull(createdPerson.getGender());
        
        assertEquals("Person Test FN", createdPerson.getFirstName());
        assertEquals("Person Test LN", createdPerson.getLastName());
        assertEquals("Person Test Address", createdPerson.getAddress());
        assertEquals("Male", createdPerson.getGender());
	}

    @Test
    @Order(2)
	public void testCreatePersonWithWrongOrigin() throws JsonMappingException, JsonProcessingException {
        mockPerson();

        specification = new RequestSpecBuilder()
                            .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_SEMERU)
                            .setBasePath("api/person/v1")
                            .setPort(TestConfigs.SERVER_PORT)
                                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                            .build();

		var content = given()
                        .spec(specification)
                        .contentType(TestConfigs.CONTENT_TYPE_JSON)
                            .body(person)
                            .when()
                            .post()
						.then()
							.statusCode(403)
						.extract()
							.body()
								.asString();

        assertNotNull(content);
        assertEquals("Invalid CORS request", content);
	}

    @Test
    @Order(3)
	public void testFindById() throws JsonMappingException, JsonProcessingException {
        mockPerson();

        specification = new RequestSpecBuilder()
                            .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_ERUDIO)
                            .setBasePath("api/person/v1")
                            .setPort(TestConfigs.SERVER_PORT)
                                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                            .build();

		var content = given()
                        .spec(specification)
                        .contentType(TestConfigs.CONTENT_TYPE_JSON)
                            .pathParam("id", person.getId())
                            .when()
                            .get("{id}")
						.then()
							.statusCode(200)
						.extract()
							.body()
								.asString();

        PersonVO persistedPerson = objectMapper.readValue(content, PersonVO.class);
        person = persistedPerson;
        
		assertNotNull(persistedPerson);
        
        assertNotNull(persistedPerson.getId());
        assertTrue(persistedPerson.getId() > 0);
        
        assertNotNull(persistedPerson.getFirstName());
        assertNotNull(persistedPerson.getLastName());
        assertNotNull(persistedPerson.getAddress());
        assertNotNull(persistedPerson.getGender());
        
        assertEquals("Person Test FN", persistedPerson.getFirstName());
        assertEquals("Person Test LN", persistedPerson.getLastName());
        assertEquals("Person Test Address", persistedPerson.getAddress());
        assertEquals("Male", persistedPerson.getGender());
	}

    @Test
    @Order(4)
	public void testFindByIdWithWrongOrigin() throws JsonMappingException, JsonProcessingException {
        mockPerson();

        specification = new RequestSpecBuilder()
                            .addHeader(TestConfigs.HEADER_PARAM_ORIGIN, TestConfigs.ORIGIN_SEMERU)
                            .setBasePath("api/person/v1")
                            .setPort(TestConfigs.SERVER_PORT)
                                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                            .build();

		var content = given()
                        .spec(specification)
                        .contentType(TestConfigs.CONTENT_TYPE_JSON)
                            .pathParam("id", person.getId())
                            .when()
                            .get("{id}")
						.then()
							.statusCode(403)
						.extract()
							.body()
								.asString();
        
		assertNotNull(content);
        assertEquals("Invalid CORS request", content);
	}

}
