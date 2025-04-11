package br.com.fiap.postech.patientapi.interfaces.dtos;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PatientUpdateDTOTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldThrowValidationExceptionWhenDateOfBirthIsInFuture() {
        PatientUpdateDTO dto = PatientUpdateDTO.builder()
                .firstName("John")
                .lastName("Doe")
                .dateOfBirth(LocalDate.now().plusDays(1))
                .build();

        Set<ConstraintViolation<PatientUpdateDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Date of Birth deve ser no passado")));
    }

    @Test
    void shouldHandleNullFieldsWhenBuildingPatientUpdateDto() {
        PatientUpdateDTO dto = PatientUpdateDTO.builder()
                .firstName(null)
                .lastName(null)
                .dateOfBirth(null)
                .phone(null)
                .number(null)
                .street(null)
                .neighborhood(null)
                .city(null)
                .state(null)
                .zipcode(null)
                .build();

        assertNull(dto.getFirstName());
        assertNull(dto.getLastName());
        assertNull(dto.getDateOfBirth());
        assertNull(dto.getPhone());
        assertNull(dto.getNumber());
        assertNull(dto.getStreet());
        assertNull(dto.getNeighborhood());
        assertNull(dto.getCity());
        assertNull(dto.getState());
        assertNull(dto.getZipcode());
    }

    @Test
    void shouldCreatePatientUpdateDtoWithAllFieldsCorrectly() {
        PatientUpdateDTO dto = PatientUpdateDTO.builder()
                .firstName("Jane")
                .lastName("Smith")
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .phone("123456789")
                .number("123")
                .street("Rua A")
                .neighborhood("Bairro B")
                .city("Cidade C")
                .state("Estado D")
                .zipcode("12345678")
                .build();

        assertEquals("Jane", dto.getFirstName());
        assertEquals("Smith", dto.getLastName());
        assertEquals(LocalDate.of(1990, 1, 1), dto.getDateOfBirth());
        assertEquals("123456789", dto.getPhone());
        assertEquals("123", dto.getNumber());
        assertEquals("Rua A", dto.getStreet());
        assertEquals("Bairro B", dto.getNeighborhood());
        assertEquals("Cidade C", dto.getCity());
        assertEquals("Estado D", dto.getState());
        assertEquals("12345678", dto.getZipcode());
    }
}