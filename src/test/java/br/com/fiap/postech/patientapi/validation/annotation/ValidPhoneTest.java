package br.com.fiap.postech.patientapi.validation.annotation;

import br.com.fiap.postech.patientapi.validation.validator.PhoneValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidPhoneTest {

    @Test
    void shouldReturnTrueWhenPhoneNumberIsValid() {
        PhoneValidator validator = new PhoneValidator();
        assertTrue(validator.isValid("38998413862", null));
    }

    @Test
    void shouldReturnFalseWhenPhoneNumberIsInvalid() {
        PhoneValidator validator = new PhoneValidator();
        assertFalse(validator.isValid("invalid-phone", null));
    }

    @Test
    void shouldReturnTrueWhenPhoneNumberIsNull() {
        PhoneValidator validator = new PhoneValidator();
        assertTrue(validator.isValid(null, null));
    }

    @Test
    void shouldReturnTrueWhenPhoneNumberIsEmpty() {
        PhoneValidator validator = new PhoneValidator();
        assertTrue(validator.isValid("", null));
    }

    @Test
    void shouldReturnTrueWhenPhoneNumberIsBlank() {
        PhoneValidator validator = new PhoneValidator();
        assertTrue(validator.isValid("   ", null));
    }

}
