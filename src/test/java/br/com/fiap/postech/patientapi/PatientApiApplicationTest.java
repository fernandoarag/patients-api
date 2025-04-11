package br.com.fiap.postech.patientapi;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PatientApiApplicationTest {

    @Test
    void shouldRunApplicationWithoutExceptions() {
        assertDoesNotThrow(() -> PatientApiApplication.main(new String[]{}));
    }
}