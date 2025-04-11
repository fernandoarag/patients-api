package br.com.fiap.postech.patientapi.util;

import static org.junit.jupiter.api.Assertions.*;

class ZipCodeUtilTest {

    void shouldRemoveFormattingFromZipCode() {
        String formattedZipCode = "12345-678";
        String result = ZipCodeUtil.removeFormatting(formattedZipCode);
        assertEquals("12345678", result);
    }

    void shouldReturnNullWhenRemovingFormattingFromNullZipCode() {
        assertNull(ZipCodeUtil.removeFormatting(null));
    }

    void shouldFormatZipCodeCorrectly() {
        String unformattedZipCode = "12345678";
        String result = ZipCodeUtil.format(unformattedZipCode);
        assertEquals("12345-678", result);
    }

    void shouldReturnOriginalZipCodeWhenFormattingInvalidZipCode() {
        String invalidZipCode = "1234";
        String result = ZipCodeUtil.format(invalidZipCode);
        assertEquals("1234", result);
    }

    void shouldReturnNullWhenFormattingNullZipCode() {
        assertNull(ZipCodeUtil.format(null));
    }

    void shouldValidateValidZipCode() {
        String validZipCode = "12345-678";
        assertTrue(ZipCodeUtil.isValid(validZipCode));
    }

    void shouldInvalidateZipCodeWithIncorrectLength() {
        String invalidZipCode = "1234";
        assertFalse(ZipCodeUtil.isValid(invalidZipCode));
    }

    void shouldInvalidateZipCodeWithNonNumericCharacters() {
        String invalidZipCode = "12345-ABC";
        assertFalse(ZipCodeUtil.isValid(invalidZipCode));
    }

    void shouldInvalidateNullZipCode() {
        assertFalse(ZipCodeUtil.isValid(null));
    }

}