package br.com.fiap.postech.patientapi.validation.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ZipCodeValidatorTest {

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

    @Test
    void shouldReturnTrueWhenZipCodeIsValid() {
        ZipCodeValidator validator = new ZipCodeValidator();
        assertTrue(validator.isValid("12345-678", null));
    }

    @Test
    void shouldReturnFalseWhenZipCodeIsInvalid() {
        ZipCodeValidator validator = new ZipCodeValidator();
        assertFalse(validator.isValid("invalid-zip", null));
    }

}