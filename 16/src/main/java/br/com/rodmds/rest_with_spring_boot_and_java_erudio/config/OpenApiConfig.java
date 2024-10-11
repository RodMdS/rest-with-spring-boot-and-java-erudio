package br.com.rodmds.rest_with_spring_boot_and_java_erudio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                        .info(new Info()
                            .title("RESTful API with Java and Spring Boot")
                            .version("v1")
                            .description("Description about my RESTful API with Java and Spring Boot")
                            .termsOfService("https://github.com/RodMdS/rest-with-spring-boot-and-java-erudio")
                            .license(new License()
                                            .name("Apache 2.0")
                                            .url("https://github.com/RodMdS/rest-with-spring-boot-and-java-erudio")
                                    )
                            );
    }

}
