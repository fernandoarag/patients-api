package br.com.fiap.postech.patientapi.validation.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PhoneValidatorTest {
    
    @Test
    void shouldReturnTrueWhenPhoneIsNull() {
        PhoneValidator validator = new PhoneValidator();
        assertTrue(validator.isValid(null, null));
    }

    @Test
    void shouldReturnTrueWhenPhoneIsEmpty() {
        PhoneValidator validator = new PhoneValidator();
        assertTrue(validator.isValid("", null));
    }

    @Test
    void shouldReturnTrueWhenPhoneIsBlank() {
        PhoneValidator validator = new PhoneValidator();
        assertTrue(validator.isValid("   ", null));
    }

    @Test
    void shouldReturnTrueWhenPhoneIsValid() {
        PhoneValidator validator = new PhoneValidator();
        assertTrue(validator.isValid("38998413862", null));
    }

    @Test
    void shouldReturnFalseWhenPhoneIsInvalid() {
        PhoneValidator validator = new PhoneValidator();
        assertFalse(validator.isValid("invalid-phone", null));
    }

}