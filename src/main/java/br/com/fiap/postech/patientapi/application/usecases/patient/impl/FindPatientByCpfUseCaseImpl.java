package br.com.fiap.postech.patientapi.application.usecases.patient.impl;

import br.com.fiap.postech.patientapi.application.exception.custom.PatientNotFoundException;
import br.com.fiap.postech.patientapi.application.usecases.patient.gateway.FindPatientByCpfUseCase;
import br.com.fiap.postech.patientapi.domain.model.Patient;
import br.com.fiap.postech.patientapi.interfaces.gateway.database.PatientGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Caso de uso para buscar um paciente pelo CPF
 */
@Service
@RequiredArgsConstructor
public class FindPatientByCpfUseCaseImpl implements FindPatientByCpfUseCase {

    private final PatientGateway patientGateway;

    public Patient execute(String cpf) {
        return patientGateway.findByCpf(cpf)
                .orElseThrow(() -> new PatientNotFoundException("cpf", cpf));
    }
}