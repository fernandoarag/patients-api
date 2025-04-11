package br.com.fiap.postech.patientapi.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for Swagger-related beans.
 */
@Configuration
public class SwaggerConfig {

    /**
     * Creates a bean for GroupedOpenApi for the public API.
     *
     * @return a GroupedOpenApi instance configured for the public API
     */
    @Bean
    GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public-api")
                .pathsToMatch("/**")
                .build();
    }

    /**
     * Creates a bean for OpenAPI with custom information.
     *
     * @return an OpenAPI instance with custom title, version, and description
     */
    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Customers API")
                        .version("v3")
                        .description("Customers API - v1"));
    }

}