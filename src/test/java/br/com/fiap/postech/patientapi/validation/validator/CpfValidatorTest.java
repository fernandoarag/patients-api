package br.com.fiap.postech.patientapi.validation.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CpfValidatorTest {

    @Test
    void shouldReturnFalseWhenCpfIsNull() {
        CpfValidator validator = new CpfValidator();
        assertFalse(validator.isValid(null, null));
    }

    @Test
    void shouldReturnFalseWhenCpfIsEmpty() {
        CpfValidator validator = new CpfValidator();
        assertFalse(validator.isValid("", null));
    }

    @Test
    void shouldReturnFalseWhenCpfIsBlank() {
        CpfValidator validator = new CpfValidator();
        assertFalse(validator.isValid("   ", null));
    }

    @Test
    void shouldReturnTrueWhenCpfIsValid() {
        CpfValidator validator = new CpfValidator();
        assertTrue(validator.isValid("123.456.789-09", null));
    }

    @Test
    void shouldReturnFalseWhenCpfIsInvalid() {
        CpfValidator validator = new CpfValidator();
        assertTrue(validator.isValid("123.456.789-00", null));
    }
}