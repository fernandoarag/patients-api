package br.com.fiap.postech.patientapi.application.usecases.patient.gateway;

import br.com.fiap.postech.patientapi.domain.model.Patient;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FindAllPatientsUseCaseTest {

    @Test
    void shouldReturnEmptyListWhenNoPatientsExist() {
        FindAllPatientsUseCase useCase = Collections::emptyList;
        List<Patient> patients = useCase.execute();

        assertNotNull(patients);
        assertEquals(0, patients.size());
    }

    @Test
    void shouldReturnListOfPatientsWhenPatientsExist() {
        FindAllPatientsUseCase useCase = () -> List.of(new Patient(1L, "Alice", "Doe", "email@email.com", "123.456.789-09", LocalDate.now(), "38998413862", "123", "Rua", "Bairro", "Cidade", "Estado", "00000000"), new Patient(2L, "Bob", "Doe", "email@email.com", "123.456.789-09", LocalDate.now(), "38998413862", "123", "Rua", "Bairro", "Cidade", "Estado", "00000000"));
        List<Patient> patients = useCase.execute();

        assertNotNull(patients);
        assertEquals(2, patients.size());
        assertEquals("Alice", patients.get(0).getFirstName());
        assertEquals("Bob", patients.get(1).getFirstName());
    }
}