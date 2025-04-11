package br.com.fiap.postech.patientapi.interfaces.dtos;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PatientRequestDTOTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldThrowValidationExceptionWhenFirstnameIsBlank() {
        PatientRequestDTO dto = PatientRequestDTO.builder()
                .firstName("")
                .lastName("Doe")
                .email("email@example.com")
                .cpf("123.456.789-09")
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .build();

        Set<ConstraintViolation<PatientRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Firstname is required!")));
    }

    @Test
    void shouldThrowValidationExceptionWhenEmailIsInvalid() {
        PatientRequestDTO dto = PatientRequestDTO.builder()
                .firstName("John")
                .lastName("Doe")
                .email("invalid-email")
                .cpf("123.456.789-09")
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .build();

        Set<ConstraintViolation<PatientRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Email is invalid!")));
    }

    @Test
    void shouldThrowValidationExceptionWhenCpfIsInvalid() {
        PatientRequestDTO dto = PatientRequestDTO.builder()
                .firstName("John")
                .lastName("Doe")
                .email("email@example.com")
                .cpf("invalid-cpf")
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .build();

        Set<ConstraintViolation<PatientRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("CPF is invalid!")));
    }

    @Test
    void shouldThrowValidationExceptionWhenDateOfBirthIsInFuture() {
        PatientRequestDTO dto = PatientRequestDTO.builder()
                .firstName("John")
                .lastName("Doe")
                .email("email@example.com")
                .cpf("123.456.789-09")
                .dateOfBirth(LocalDate.now().plusDays(1))
                .build();

        Set<ConstraintViolation<PatientRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Date of Birth deve ser no passado")));
    }

    @Test
    void shouldPassValidationWhenAllFieldsAreValid() {
        PatientRequestDTO dto = PatientRequestDTO.builder()
                .firstName("John")
                .lastName("Doe")
                .email("email@example.com")
                .cpf("123.456.789-09")
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .phone("123456789")
                .number("123")
                .street("Rua A")
                .neighborhood("Bairro B")
                .city("Cidade C")
                .state("Estado D")
                .zipcode("12345678")
                .build();

        Set<ConstraintViolation<PatientRequestDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }
}