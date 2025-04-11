package br.com.fiap.postech.patientapi.interfaces.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Past;
import lombok.*;

import java.time.LocalDate;

/**
 * DTO para atualização de um paciente existente
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientUpdateDTO {
    private String firstName;

    private String lastName;

    @JsonIgnore
    private String email;

    @JsonIgnore
    private String cpf;

    @Past(message = "Date of Birth deve ser no passado")
    private LocalDate dateOfBirth;

    private String phone;

    private String number;

    private String street;

    private String neighborhood;

    private String city;

    private String state;

    private String zipcode;

}