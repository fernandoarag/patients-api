package br.com.fiap.postech.patientapi.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.junit.jupiter.api.Test;
import org.springdoc.core.models.GroupedOpenApi;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SwaggerConfigTest {

    private final SwaggerConfig swaggerConfig = new SwaggerConfig();

    @Test
    void shouldReturnGroupedOpenApiWithCorrectGroupName() {
        GroupedOpenApi groupedOpenApi = swaggerConfig.publicApi();
        assertEquals("public-api", groupedOpenApi.getGroup());
    }

    @Test
    void shouldReturnOpenApiWithCorrectTitleVersionAndDescription() {
        OpenAPI openAPI = swaggerConfig.customOpenAPI();
        assertEquals("Customers API", openAPI.getInfo().getTitle());
        assertEquals("v3", openAPI.getInfo().getVersion());
        assertEquals("Customers API - v1", openAPI.getInfo().getDescription());
    }
}