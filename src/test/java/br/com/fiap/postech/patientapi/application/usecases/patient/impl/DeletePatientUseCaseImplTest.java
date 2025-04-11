package br.com.fiap.postech.patientapi.application.usecases.patient.impl;

import br.com.fiap.postech.patientapi.application.exception.custom.PatientNotFoundException;
import br.com.fiap.postech.patientapi.application.usecases.patient.gateway.FindPatientByIdUseCase;
import br.com.fiap.postech.patientapi.domain.model.Patient;
import br.com.fiap.postech.patientapi.interfaces.gateway.database.PatientGateway;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;

class DeletePatientUseCaseImplTest {

    private final PatientGateway patientGateway = Mockito.mock(PatientGateway.class);
    private final FindPatientByIdUseCase findPatientByIdUseCase = Mockito.mock(FindPatientByIdUseCase.class);
    private final DeletePatientUseCaseImpl useCase = new DeletePatientUseCaseImpl(patientGateway, findPatientByIdUseCase);

    @Test
    void shouldDeletePatientSuccessfullyWhenIdIsValid() {
        Long validId = 1L;
        doNothing().when(patientGateway).deleteById(validId);
        Mockito.doReturn(new Patient()).when(findPatientByIdUseCase).execute(validId);

        assertDoesNotThrow(() -> useCase.execute(validId));
    }

    @Test
    void shouldThrowExceptionWhenPatientDoesNotExist() {
        Long invalidId = 99L;
        Mockito.doThrow(new PatientNotFoundException("ID", invalidId.toString())).when(findPatientByIdUseCase).execute(invalidId);

        assertThrows(PatientNotFoundException.class, () -> useCase.execute(invalidId));
    }

    @Test
    void shouldThrowExceptionWhenIdIsNull() {
        assertThrows(PatientNotFoundException.class, () -> useCase.execute(null));
    }
}