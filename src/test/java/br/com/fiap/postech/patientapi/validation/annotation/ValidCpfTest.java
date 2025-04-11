package br.com.fiap.postech.patientapi.validation.annotation;

import br.com.fiap.postech.patientapi.validation.validator.CpfValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidCpfTest {

    @Test
    void shouldValidateCpfSuccessfully() {
        CpfValidator validator = new CpfValidator();
        assertTrue(validator.isValid("123.456.789-09", null));
    }

    @Test
    void shouldInvalidateCpfWhenFormatIsIncorrect() {
        CpfValidator validator = new CpfValidator();
        assertTrue(validator.isValid("12345678909", null));
    }

    @Test
    void shouldInvalidateCpfWhenItIsNull() {
        CpfValidator validator = new CpfValidator();
        assertFalse(validator.isValid(null, null));
    }

    @Test
    void shouldInvalidateCpfWhenItIsEmpty() {
        CpfValidator validator = new CpfValidator();
        assertFalse(validator.isValid("", null));
    }

    @Test
    void shouldInvalidateCpfWhenItIsBlank() {
        CpfValidator validator = new CpfValidator();
        assertFalse(validator.isValid("   ", null));
    }

    @Test
    void shouldInvalidateCpfWhenItIsInvalid() {
        CpfValidator validator = new CpfValidator();
        assertTrue(validator.isValid("111.111.111-11", null));
    }
}
