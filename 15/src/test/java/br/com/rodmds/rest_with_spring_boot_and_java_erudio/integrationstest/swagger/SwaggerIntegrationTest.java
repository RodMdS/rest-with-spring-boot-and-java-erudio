package br.com.rodmds.rest_with_spring_boot_and_java_erudio.integrationstest.swagger;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.rodmds.rest_with_spring_boot_and_java_erudio.configs.TestConfigs;
import br.com.rodmds.rest_with_spring_boot_and_java_erudio.integrationstest.testcontainers.AbstractIntegrationTest;
import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SwaggerIntegrationTest extends AbstractIntegrationTest {

	@Test
	public void shouldDisplaySwaggerUiPage() {
		var content = given()
						.basePath("/swagger-ui/index.html")
						.port(TestConfigs.SERVER_PORT)
						.when()
							.get()
						.then()
							.statusCode(200)
						.extract()
							.body()
								.asString();
		assertTrue(content.contains("Swagger UI"));
	}

}
