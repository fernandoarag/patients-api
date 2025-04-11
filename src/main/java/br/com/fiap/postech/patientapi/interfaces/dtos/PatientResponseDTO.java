package br.com.fiap.postech.patientapi.interfaces.dtos;

import lombok.*;

import java.time.LocalDate;

/**
 * DTO para resposta com os dados de um paciente
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String cpf;
    private LocalDate dateOfBirth;
    private String phone;
    private String number;
    private String street;
    private String neighborhood;
    private String city;
    private String state;
    private String zipcode;
}