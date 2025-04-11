package br.com.fiap.postech.patientapi.infrastructure.entity;


import br.com.fiap.postech.patientapi.infrastructure.converter.CpfConverter;
import br.com.fiap.postech.patientapi.infrastructure.converter.PhoneConverter;
import br.com.fiap.postech.patientapi.infrastructure.converter.ZipCodeConverter;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * Entidade JPA para persistência de Paciente no banco de dados.
 * Esta classe pertence à camada de infraestrutura.
 */
@Entity
@Table(name = "TB_PATIENTS")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    @Convert(converter = CpfConverter.class)
    private String cpf;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column
    @Convert(converter = PhoneConverter.class)
    private String phone;

    @Column
    private String number;

    @Column
    private String street;

    @Column
    private String neighborhood;

    @Column
    private String city;

    @Column
    private String state;

    @Column
    @Convert(converter = ZipCodeConverter.class)
    private String zipcode;
}