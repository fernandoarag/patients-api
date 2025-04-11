package br.com.fiap.postech.patientapi.application.usecases.patient.impl;


import br.com.fiap.postech.patientapi.application.exception.custom.PatientNotFoundException;
import br.com.fiap.postech.patientapi.application.usecases.patient.gateway.DeletePatientUseCase;
import br.com.fiap.postech.patientapi.application.usecases.patient.gateway.FindPatientByIdUseCase;
import br.com.fiap.postech.patientapi.interfaces.gateway.database.PatientGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Caso de uso para excluir um paciente
 */
@Service
@RequiredArgsConstructor
public class DeletePatientUseCaseImpl implements DeletePatientUseCase {

    private final PatientGateway patientGateway;
    private final FindPatientByIdUseCase findPatientByIdUseCase;

    public void execute(Long id) {
        if (id == null) {
            throw new PatientNotFoundException("ID", "null");
        }

        // Verifica se o paciente existe
        findPatientByIdUseCase.execute(id);

        // Se não lançou exceção, o paciente existe
        patientGateway.deleteById(id);
    }
}