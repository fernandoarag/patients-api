package br.com.fiap.postech.patientapi.validation.annotation;

import br.com.fiap.postech.patientapi.validation.validator.ZipCodeValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidZipCodeTest {

    @Test
    void shouldReturnTrueWhenZipCodeIsValid() {
        ZipCodeValidator validator = new ZipCodeValidator();
        assertTrue(validator.isValid("12345678", null));
    }

    @Test
    void shouldReturnFalseWhenZipCodeIsInvalid() {
        ZipCodeValidator validator = new ZipCodeValidator();
        assertFalse(validator.isValid("invalid-zip", null));
    }

    @Test
    void shouldReturnTrueWhenZipCodeIsNull() {
        ZipCodeValidator validator = new ZipCodeValidator();
        assertTrue(validator.isValid(null, null));
    }

    @Test
    void shouldReturnTrueWhenZipCodeIsEmpty() {
        ZipCodeValidator validator = new ZipCodeValidator();
        assertTrue(validator.isValid("", null));
    }

    @Test
    void shouldReturnTrueWhenZipCodeIsBlank() {
        ZipCodeValidator validator = new ZipCodeValidator();
        assertTrue(validator.isValid("   ", null));
    }

}
