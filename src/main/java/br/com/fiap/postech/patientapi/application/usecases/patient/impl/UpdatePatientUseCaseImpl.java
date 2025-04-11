package br.com.fiap.postech.patientapi.application.usecases.patient.impl;

import br.com.fiap.postech.patientapi.application.exception.custom.PatientCpfAlreadyExistsException;
import br.com.fiap.postech.patientapi.application.usecases.patient.gateway.FindPatientByIdUseCase;
import br.com.fiap.postech.patientapi.application.usecases.patient.gateway.UpdatePatientUseCase;
import br.com.fiap.postech.patientapi.domain.model.Patient;
import br.com.fiap.postech.patientapi.interfaces.gateway.database.PatientGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Caso de uso para atualizar os dados de um paciente
 */
@Service
@RequiredArgsConstructor
public class UpdatePatientUseCaseImpl implements UpdatePatientUseCase {

    private final PatientGateway patientGateway;
    private final FindPatientByIdUseCase findPatientByIdUseCase;

    public Patient execute(Long id, Patient updatedPatient) {
        Patient existedPatient = findPatientByIdUseCase.execute(id);

        // Verificar se está tentando mudar o CPF para um que já existe
        if (!existedPatient.getCpf().equals(updatedPatient.getCpf()) &&
                patientGateway.existsByCpf(updatedPatient.getCpf())) {
            throw new PatientCpfAlreadyExistsException(updatedPatient.getCpf());
        }

        updatedPatient.setId(id);
        if (updatedPatient.getFirstName() == null) {
            updatedPatient.setFirstName(existedPatient.getFirstName());
        }
        if (updatedPatient.getLastName() == null) {
            updatedPatient.setLastName(existedPatient.getLastName());
        }
        updatedPatient.setEmail(existedPatient.getEmail());
        updatedPatient.setCpf(existedPatient.getCpf());
        if (updatedPatient.getDateOfBirth() == null) {
            updatedPatient.setDateOfBirth(existedPatient.getDateOfBirth());
        }
        if (updatedPatient.getPhone() == null) {
            updatedPatient.setPhone(existedPatient.getPhone());
        }
        if (updatedPatient.getNumber() == null) {
            updatedPatient.setNumber(existedPatient.getNumber());
        }
        if (updatedPatient.getStreet() == null) {
            updatedPatient.setStreet(existedPatient.getStreet());
        }
        if (updatedPatient.getNeighborhood() == null) {
            updatedPatient.setNeighborhood(existedPatient.getNeighborhood());
        }
        if (updatedPatient.getCity() == null) {
            updatedPatient.setCity(existedPatient.getCity());
        }
        if (updatedPatient.getState() == null) {
            updatedPatient.setState(existedPatient.getState());
        }
        if (updatedPatient.getZipcode() == null) {
            updatedPatient.setZipcode(existedPatient.getZipcode());
        }

        return patientGateway.save(updatedPatient);
    }
}