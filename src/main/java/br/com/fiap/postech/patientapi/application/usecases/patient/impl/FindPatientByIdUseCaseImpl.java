package br.com.fiap.postech.patientapi.application.usecases.patient.impl;

import br.com.fiap.postech.patientapi.application.exception.custom.PatientNotFoundException;
import br.com.fiap.postech.patientapi.application.usecases.patient.gateway.FindPatientByIdUseCase;
import br.com.fiap.postech.patientapi.domain.model.Patient;
import br.com.fiap.postech.patientapi.interfaces.gateway.database.PatientGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Caso de uso para buscar um paciente pelo ID
 */
@Service
@RequiredArgsConstructor
public class FindPatientByIdUseCaseImpl implements FindPatientByIdUseCase {

    private final PatientGateway patientGateway;

    public Patient execute(Long id) {
        if (id == null) {
            throw new PatientNotFoundException("ID", "não enviado");
        }
        return patientGateway.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("ID", id.toString()));
    }
}