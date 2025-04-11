package br.com.fiap.postech.patientapi.application.exception.custom;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PatientCpfAlreadyExistsExceptionTest {

    @Test
    void shouldReturnCorrectMessageWhenExceptionIsThrown() {
        String cpf = "123.456.789-09";
        PatientCpfAlreadyExistsException exception = new PatientCpfAlreadyExistsException(cpf);
        assertEquals("Patient with cpf: '123.456.789-09' already exists!", exception.getMessage());
    }

    @Test
    void shouldReturnCorrectErrorType() {
        PatientCpfAlreadyExistsException exception = new PatientCpfAlreadyExistsException("123.456.789-09");
        assertEquals("ALREADY_EXISTS", exception.getType());
    }

    @Test
    void shouldReturnCorrectErrorTitle() {
        PatientCpfAlreadyExistsException exception = new PatientCpfAlreadyExistsException("123.456.789-09");
        assertEquals("Patient Already Exists", exception.getTitle());
    }

    @Test
    void shouldReturnCorrectHttpStatusCode() {
        PatientCpfAlreadyExistsException exception = new PatientCpfAlreadyExistsException("123.456.789-09");
        assertEquals(409, exception.getStatus());
    }
}