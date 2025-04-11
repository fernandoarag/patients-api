/* Definição do esquema do banco de dados */

-- Tabela de pacientes
CREATE TABLE IF NOT EXISTS TB_PATIENTS
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name    VARCHAR(255) NOT NULL,
    last_name     VARCHAR(255) NOT NULL,
    email         VARCHAR(255) NOT NULL UNIQUE,
    cpf           VARCHAR(14)  NOT NULL UNIQUE,
    date_of_birth DATE         NOT NULL,
    phone         VARCHAR(20),
    number        VARCHAR(20),
    street        VARCHAR(255),
    neighborhood  VARCHAR(255),
    city          VARCHAR(255),
    state         VARCHAR(50),
    zipcode       VARCHAR(10)
);