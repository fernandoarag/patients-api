package br.com.fiap.postech.patientapi.infrastructure.converter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ZipCodeConverterTest {

    private final ZipCodeConverter zipCodeConverter = new ZipCodeConverter();

    @Test
    void shouldConvertFormattedZipCodeToDatabaseColumn() {
        String formattedZipCode = "12345-678";
        String result = zipCodeConverter.convertToDatabaseColumn(formattedZipCode);
        assertEquals("12345678", result);
    }

    @Test
    void shouldReturnNullWhenConvertingNullZipCodeToDatabaseColumn() {
        String result = zipCodeConverter.convertToDatabaseColumn(null);
        assertNull(result);
    }

    @Test
    void shouldReturnZipCodeAsIsWhenConvertingFromDatabaseColumn() {
        String zipCodeFromDatabase = "12345678";
        String result = zipCodeConverter.convertToEntityAttribute(zipCodeFromDatabase);
        assertEquals("12345678", result);
    }

    @Test
    void shouldReturnNullWhenConvertingNullZipCodeFromDatabaseColumn() {
        String result = zipCodeConverter.convertToEntityAttribute(null);
        assertNull(result);
    }
}