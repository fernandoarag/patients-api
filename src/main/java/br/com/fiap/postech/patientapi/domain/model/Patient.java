package br.com.fiap.postech.patientapi.domain.model;

import lombok.*;

import java.time.LocalDate;

/**
 * Entidade de domínio que representa um Paciente.
 * Esta classe pertence à camada de domínio e não possui dependências
 * com frameworks ou infraestrutura.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

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