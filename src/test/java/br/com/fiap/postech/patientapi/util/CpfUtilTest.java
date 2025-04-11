package br.com.fiap.postech.patientapi.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CpfUtilTest {

    @Test
    void shouldRemoveFormattingFromCpf() {
        String formattedCpf = "123.456.789-09";
        String result = CpfUtil.removeFormatting(formattedCpf);
        assertEquals("12345678909", result);
    }

    @Test
    void shouldReturnNullWhenRemovingFormattingFromNullCpf() {
        assertNull(CpfUtil.removeFormatting(null));
    }

    @Test
    void shouldFormatCpfCorrectly() {
        String unformattedCpf = "12345678909";
        String result = CpfUtil.format(unformattedCpf);
        assertEquals("123.456.789-09", result);
    }

    @Test
    void shouldReturnOriginalCpfWhenFormattingInvalidCpf() {
        String invalidCpf = "12345";
        String result = CpfUtil.format(invalidCpf);
        assertEquals("12345", result);
    }

    @Test
    void shouldReturnNullWhenFormattingNullCpf() {
        assertNull(CpfUtil.format(null));
    }

    @Test
    void shouldValidateValidCpf() {
        String validCpf = "123.456.789-09";
        assertTrue(CpfUtil.isValid(validCpf));
    }

    @Test
    void shouldInvalidateCpfWithIncorrectLength() {
        String invalidCpf = "12345";
        assertFalse(CpfUtil.isValid(invalidCpf));
    }

    @Test
    void shouldInvalidateCpfWithNonNumericCharacters() {
        String invalidCpf = "123.456.789-AB";
        assertFalse(CpfUtil.isValid(invalidCpf));
    }

    @Test
    void shouldInvalidateNullCpf() {
        assertFalse(CpfUtil.isValid(null));
    }
}