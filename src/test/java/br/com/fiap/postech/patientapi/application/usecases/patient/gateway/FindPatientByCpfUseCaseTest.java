package br.com.fiap.postech.patientapi.application.usecases.patient.gateway;

import br.com.fiap.postech.patientapi.domain.model.Patient;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FindPatientByCpfUseCaseTest {

    @Test
    void shouldReturnPatientWhenCpfIsValid() {
        FindPatientByCpfUseCase useCase = cpf -> new Patient(1L, "John", "Doe", "email@email.com", "123.456.789-00", LocalDate.now(), "38998413862", "123", "Rua", "Bairro", "Cidade", "Estado", "00000000");
        Patient patient = useCase.execute("123.456.789-00");

        assertEquals("123.456.789-00", patient.getCpf());
        assertEquals("John", patient.getFirstName());
    }

    @Test
    void shouldThrowExceptionWhenCpfIsNull() {
        FindPatientByCpfUseCase useCase = cpf -> {
            if (cpf == null) {
                throw new IllegalArgumentException("CPF cannot be null");
            }
            return null;
        };

        assertThrows(IllegalArgumentException.class, () -> useCase.execute(null));
    }

    @Test
    void shouldThrowExceptionWhenCpfIsInvalid() {
        FindPatientByCpfUseCase useCase = cpf -> {
            if (!cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
                throw new IllegalArgumentException("Invalid CPF format");
            }
            return null;
        };

        assertThrows(IllegalArgumentException.class, () -> useCase.execute("invalid-cpf"));
    }
}