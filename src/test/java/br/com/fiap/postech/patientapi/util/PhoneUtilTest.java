package br.com.fiap.postech.patientapi.util;

import static org.junit.jupiter.api.Assertions.*;

class PhoneUtilTest {

    void shouldRemoveFormattingFromPhone() {
        String formattedPhone = "(11) 98765-4321";
        String result = PhoneUtil.removeFormatting(formattedPhone);
        assertEquals("11987654321", result);
    }

    void shouldReturnNullWhenRemovingFormattingFromNullPhone() {
        assertNull(PhoneUtil.removeFormatting(null));
    }

    void shouldFormatPhoneCorrectlyForMobile() {
        String unformattedPhone = "11987654321";
        String result = PhoneUtil.format(unformattedPhone);
        assertEquals("(11) 98765-4321", result);
    }

    void shouldFormatPhoneCorrectlyForLandline() {
        String unformattedPhone = "1134567890";
        String result = PhoneUtil.format(unformattedPhone);
        assertEquals("(11) 3456-7890", result);
    }

    void shouldReturnOriginalPhoneWhenFormattingInvalidPhone() {
        String invalidPhone = "12345";
        String result = PhoneUtil.format(invalidPhone);
        assertEquals("12345", result);
    }

    void shouldReturnNullWhenFormattingNullPhone() {
        assertNull(PhoneUtil.format(null));
    }

    void shouldValidateValidMobilePhone() {
        String validPhone = "(11) 98765-4321";
        assertTrue(PhoneUtil.isValid(validPhone));
    }

    void shouldValidateValidLandlinePhone() {
        String validPhone = "(11) 3456-7890";
        assertTrue(PhoneUtil.isValid(validPhone));
    }

    void shouldInvalidatePhoneWithIncorrectLength() {
        String invalidPhone = "12345";
        assertFalse(PhoneUtil.isValid(invalidPhone));
    }

    void shouldInvalidatePhoneWithNonNumericCharacters() {
        String invalidPhone = "(11) ABCDE-4321";
        assertFalse(PhoneUtil.isValid(invalidPhone));
    }

    void shouldInvalidateNullPhone() {
        assertFalse(PhoneUtil.isValid(null));
    }
}