package br.com.fiap.postech.patientapi.infrastructure.converter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CpfConverterTest {

    private final CpfConverter cpfConverter = new CpfConverter();

    @Test
    void shouldConvertFormattedCpfToDatabaseColumn() {
        String formattedCpf = "123.456.789-09";
        String result = cpfConverter.convertToDatabaseColumn(formattedCpf);
        assertEquals("12345678909", result);
    }

    @Test
    void shouldReturnNullWhenConvertingNullCpfToDatabaseColumn() {
        String result = cpfConverter.convertToDatabaseColumn(null);
        assertNull(result);
    }

    @Test
    void shouldReturnCpfAsIsWhenConvertingFromDatabaseColumn() {
        String cpfFromDatabase = "12345678909";
        String result = cpfConverter.convertToEntityAttribute(cpfFromDatabase);
        assertEquals("12345678909", result);
    }

    @Test
    void shouldReturnNullWhenConvertingNullCpfFromDatabaseColumn() {
        String result = cpfConverter.convertToEntityAttribute(null);
        assertNull(result);
    }
}