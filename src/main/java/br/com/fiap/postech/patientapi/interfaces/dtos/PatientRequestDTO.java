package br.com.fiap.postech.patientapi.interfaces.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

/**
 * DTO para criação de um novo paciente
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientRequestDTO {
    @NotBlank(message = "Firstname is required!")
    private String firstName;

    @NotBlank(message = "Lastname is required!")
    private String lastName;

    @NotBlank(message = "Email is required!")
    @Email(message = "Email is invalid!")
    private String email;

    @NotBlank(message = "CPF is required!")
    @CPF(message = "CPF is invalid!")
    private String cpf;

    @NotNull(message = "Date of Birth is required!")
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