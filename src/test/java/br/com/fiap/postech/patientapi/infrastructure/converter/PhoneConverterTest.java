package br.com.fiap.postech.patientapi.infrastructure.converter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PhoneConverterTest {

    private final PhoneConverter phoneConverter = new PhoneConverter();

    @Test
    void shouldConvertFormattedPhoneToDatabaseColumn() {
        String formattedPhone = "(11) 91234-5678";
        String result = phoneConverter.convertToDatabaseColumn(formattedPhone);
        assertEquals("11912345678", result);
    }

    @Test
    void shouldReturnNullWhenConvertingNullPhoneToDatabaseColumn() {
        String result = phoneConverter.convertToDatabaseColumn(null);
        assertNull(result);
    }

    @Test
    void shouldReturnPhoneAsIsWhenConvertingFromDatabaseColumn() {
        String phoneFromDatabase = "11912345678";
        String result = phoneConverter.convertToEntityAttribute(phoneFromDatabase);
        assertEquals("11912345678", result);
    }

    @Test
    void shouldReturnNullWhenConvertingNullPhoneFromDatabaseColumn() {
        String result = phoneConverter.convertToEntityAttribute(null);
        assertNull(result);
    }
}