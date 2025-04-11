package br.com.fiap.postech.patientapi.infrastructure.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PatientEntityTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    void shouldCreatePatientEntityWithAllFields() {
        PatientEntity patientEntity = PatientEntity.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@email.com")
                .cpf("123.456.789-09")
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .phone("38998413862")
                .number("123")
                .street("Rua")
                .neighborhood("Bairro")
                .city("Cidade")
                .state("Estado")
                .zipcode("12345678")
                .build();

        assertEquals(1L, patientEntity.getId());
        assertEquals("John", patientEntity.getFirstName());
        assertEquals("Doe", patientEntity.getLastName());
        assertEquals("john.doe@email.com", patientEntity.getEmail());
        assertEquals("123.456.789-09", patientEntity.getCpf());
        assertEquals(LocalDate.of(1990, 1, 1), patientEntity.getDateOfBirth());
        assertEquals("38998413862", patientEntity.getPhone());
        assertEquals("123", patientEntity.getNumber());
        assertEquals("Rua", patientEntity.getStreet());
        assertEquals("Bairro", patientEntity.getNeighborhood());
        assertEquals("Cidade", patientEntity.getCity());
        assertEquals("Estado", patientEntity.getState());
        assertEquals("12345678", patientEntity.getZipcode());
    }

    @Test
    @Transactional
    void shouldThrowExceptionWhenMandatoryFieldsAreMissing() {
        PatientEntity patientEntity = PatientEntity.builder()
                .firstName(null)
                .lastName(null)
                .email(null)
                .cpf(null)
                .dateOfBirth(null)
                .build();

        assertThrows(ConstraintViolationException.class, () -> {
            entityManager.persist(patientEntity);
            entityManager.flush(); // Força a validação e persistência no banco
        });
    }

    @Test
    @Transactional
    void shouldPersistPatientEntityWithAllFields() {
        PatientEntity patientEntity = PatientEntity.builder()
                .firstName("Jane")
                .lastName("Smith")
                .email("jane.smith@email.com")
                .cpf("98765867100")
                .dateOfBirth(LocalDate.of(1985, 5, 15))
                .phone("11987654321")
                .number("456")
                .street("Avenida")
                .neighborhood("Centro")
                .city("São Paulo")
                .state("SP")
                .zipcode("87654321")
                .build();

        entityManager.persist(patientEntity);
        entityManager.flush();

        PatientEntity found = entityManager.find(PatientEntity.class, patientEntity.getId());
        assertEquals("Jane", found.getFirstName());
        assertEquals("Smith", found.getLastName());
        assertEquals("jane.smith@email.com", found.getEmail());
        assertEquals("98765867100", found.getCpf());
        assertEquals(LocalDate.of(1985, 5, 15), found.getDateOfBirth());
        assertEquals("11987654321", found.getPhone());
        assertEquals("456", found.getNumber());
        assertEquals("Avenida", found.getStreet());
        assertEquals("Centro", found.getNeighborhood());
        assertEquals("São Paulo", found.getCity());
        assertEquals("SP", found.getState());
        assertEquals("87654321", found.getZipcode());
    }

    @Test
    @Transactional
    void shouldThrowExceptionWhenPersistingWithDuplicateEmail() {
        PatientEntity patientEntity1 = PatientEntity.builder()
                .firstName("Alice")
                .lastName("Brown")
                .email("alice.brown@email.com")
                .cpf("111.222.333-44")
                .dateOfBirth(LocalDate.of(1992, 3, 10))
                .build();

        PatientEntity patientEntity2 = PatientEntity.builder()
                .firstName("Bob")
                .lastName("White")
                .email("alice.brown@email.com")
                .cpf("555.666.777-88")
                .dateOfBirth(LocalDate.of(1990, 7, 20))
                .build();

        entityManager.persist(patientEntity1);
        entityManager.flush();

        assertThrows(PersistenceException.class, () -> {
            entityManager.persist(patientEntity2);
            entityManager.flush();
        });
    }

    @Test
    @Transactional
    void shouldThrowExceptionWhenPersistingWithNullMandatoryFields() {
        PatientEntity patientEntity = PatientEntity.builder()
                .firstName(null)
                .lastName(null)
                .email(null)
                .cpf(null)
                .dateOfBirth(null)
                .build();

        assertThrows(PersistenceException.class, () -> {
            entityManager.persist(patientEntity);
            entityManager.flush();
        });
    }

    @Test
    @Transactional
    void shouldThrowExceptionWhenPersistingWithDuplicateCpf() {
        PatientEntity patientEntity1 = PatientEntity.builder()
                .firstName("Carlos")
                .lastName("Silva")
                .email("carlos.silva@email.com")
                .cpf("12384397900")
                .dateOfBirth(LocalDate.of(1980, 4, 25))
                .build();

        PatientEntity patientEntity2 = PatientEntity.builder()
                .firstName("Ana")
                .lastName("Souza")
                .email("ana.souza@email.com")
                .cpf("12384397900")
                .dateOfBirth(LocalDate.of(1990, 6, 15))
                .build();

        entityManager.persist(patientEntity1);
        entityManager.flush();

        assertThrows(PersistenceException.class, () -> {
            entityManager.persist(patientEntity2);
            entityManager.flush();
        });
    }

    @Test
    @Transactional
    void shouldPersistPatientEntityWithoutOptionalFields() {
        PatientEntity patientEntity = PatientEntity.builder()
                .firstName("Mariana")
                .lastName("Lima")
                .email("mariana.lima@email.com")
                .cpf("91235432100")
                .dateOfBirth(LocalDate.of(1995, 8, 20))
                .build();

        entityManager.persist(patientEntity);
        entityManager.flush();

        PatientEntity found = entityManager.find(PatientEntity.class, patientEntity.getId());
        assertEquals("Mariana", found.getFirstName());
        assertEquals("Lima", found.getLastName());
        assertEquals("mariana.lima@email.com", found.getEmail());
        assertEquals("91235432100", found.getCpf());
        assertEquals(LocalDate.of(1995, 8, 20), found.getDateOfBirth());
        assertNull(found.getPhone());
        assertNull(found.getNumber());
        assertNull(found.getStreet());
        assertNull(found.getNeighborhood());
        assertNull(found.getCity());
        assertNull(found.getState());
        assertNull(found.getZipcode());
    }
}