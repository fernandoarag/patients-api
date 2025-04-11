# Pacientes API

API REST para gerenciamento de cadastro de pacientes, desenvolvida com Spring Boot e Maven seguindo princípios de
Arquitetura Limpa.

## 📋 Sumário

- [Visão Geral](#visão-geral)
- [Arquitetura](#arquitetura)
- [Tecnologias](#tecnologias)
- [Pré-requisitos](#pré-requisitos)
- [Instalação e Execução](#instalação-e-execução)
- [Endpoints da API](#endpoints-da-api)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Tratamento de Exceções](#tratamento-de-exceções)
- [Desenvolvimento](#desenvolvimento)
- [Contribuindo](#contribuindo)
- [Licença](#licença)

## 🔍 Visão Geral

Pacientes API é uma solução backend para gerenciamento de cadastro de pacientes em ambiente hospitalar ou clínico. Ela
implementa operações CRUD (Create, Read, Update, Delete) seguindo princípios de Clean Architecture e Clean Code.

## 🏗 Arquitetura

O projeto segue os princípios da Arquitetura Limpa (Clean Architecture), organizado nas seguintes camadas:

1. **Domain (Camada de Domínio)**: Contém as entidades de negócio, regras de negócio (use cases) e interfaces de
   repositório (portas). Esta camada é independente de frameworks externos.

2. **Infrastructure (Camada de Infraestrutura)**: Implementa as interfaces definidas na camada de domínio, como os
   repositórios JPA, mappers, e outras funcionalidades de infraestrutura.

3. **API (Camada de Interface)**: Contém os controladores REST, DTOs e handlers de exceções que expõem a funcionalidade
   da aplicação ao mundo externo.

## 🛠 Tecnologias

- **Java 17**
- **Spring Boot 3.2.3**
- **Spring Data JPA**
- **Maven**
- **H2 Database**
- **MapStruct**
- **Lombok**
- **Hibernate Validator**

## 📝 Pré-requisitos

- JDK 17 ou superior
- Maven 3.6 ou superior
- IDE com suporte para desenvolvimento Java (recomendado: IntelliJ IDEA, Eclipse, VSCode)

## 🚀 Instalação e Execução

1. Clone o repositório:
   ```bash
   git clone https://github.com/fernandoarag/patients-api.git
   cd patients-api
   ```

2. Compile o projeto:
   ```bash
   mvn clean install
   ```

3. Execute a aplicação:
   ```bash
   mvn spring-boot:run
   ```

4. A API estará disponível em:
    - http://localhost:8080/api/patients-system/v1

5. A documentação Swagger estará disponível em:
    - http://localhost:8080/api/patients-system/v1/swagger-ui/index.html

6. Console H2 (banco de dados):
    - URL: http://localhost:8080/api/patients-system/v1/h2-console/
    - JDBC jdbc:h2:/data/pacientesdb;AUTO_SERVER=TRUE;
    - Usuário: sa
    - Senha: password

## 📡 Endpoints da API

| Método | Endpoint                  | Descrição                        |
|--------|---------------------------|----------------------------------|
| POST   | `/api/patients`           | Cria um novo paciente            |
| GET    | `/api/patients`           | Lista todos os pacientes         |
| GET    | `/api/patients/{id}`      | Busca um paciente pelo ID        |
| GET    | `/api/patients/cpf/{cpf}` | Busca um paciente pelo CPF       |
| PUT    | `/api/patients/{id}`      | Atualiza os dados de um paciente |
| DELETE | `/api/patients/{id}`      | Remove um paciente               |

### Exemplos de Requisições

#### Criar Paciente

```http
POST /api/patients-system/v1/patients
Content-Type: application/json

{
   "firstName": "First Name",
   "lastName": "Last Name",
   "email": "email@email.com",
   "cpf": "529.982.247-25",
   "dateOfBirth": "1995-11-20",
   "phone": "(11) 94321-8765",
   "number": "1200",
   "street": "Avenida teste",
   "neighborhood": "teste",
   "city": "teste",
   "state": "TESTE",
   "zipcode": "00000-000"
}
```

#### Buscar Todos os Pacientes

```http
GET /api/patients-system/v1/patients
```

#### Buscar Paciente por ID

```http
GET /api/patients-system/v1/patients/6
```

#### Buscar Paciente por CPF

```http
GET /api/patients-system/v1/patients/cpf/98765432100
```

#### Atualizar Paciente

```http
PUT /api/patients-system/v1/patients/1
Content-Type: application/json

{
  "firstName": "Name Updated",
  "lastName": "Last name updated",
}
```

#### Excluir Paciente

```http
DELETE /api/patients-system/v1/patients/1
```

## 📂 Estrutura do Projeto

```
com.hospital.pacientesapi
├── application                 # Camada de aplicação
│   ├── exception               # Manipulador global de exceções
│   └── usecase                 # Casos de uso (regras de negócio)
├── domain                      # Camada de domínio
│   └── model                   # Modelo da entidade de domínio
├── infrastructure              # Camada de infraestrutura
│   ├── config                  # Configurações da aplicação e Swagger
│   ├── converter               # Conversores de dados
│   ├── entity                  # Entidades de domínio
│   ├── mapper                  # Mappers para converter entidades JPA para domínio
│   └── repository              # Interfaces (portas) de repositório
├── interfaces                  # Camada de interface
│   ├── adapters                # Rest Adapters para converter DTOs para entidades de domínio
│   ├── dtos                    # DTOs para entrada e saída
│   ├── gateway                 # Implementação do JPA gateway para abstrair camadas de persistência
│   └── rest                    # Controladores REST
├── util                        # Camada de funções úteis(Formatadores)
├── validation                  # Camada de validators customizados
│   ├── annotation             # Anotações de validação
│   └── validator              # Implementação de validadores
└── PacientesApiApplication.java # Classe principal
```

## 🔧 Tratamento de Exceções

A API implementa um tratamento de exceções centralizado para retornar mensagens de erro consistentes:

- **400 Bad Request**: Erros de validação de dados de entrada ou dados inválidos
- **404 Not Found**: Quando um paciente não é encontrado
- **409 Conflict**: Quando há tentativa de cadastrar um CPF já existente
- **500 Internal Server Error**: Erros internos não tratados

## 👨‍💻 Desenvolvimento

Para contribuir com o desenvolvimento:

1. Crie um branch para sua feature: `git checkout -b feature/#codIssue-nova-funcionalidade`
2. Faça suas alterações e commit: `git commit -m 'Adiciona nova funcionalidade'`
3. Envie para o branch: `git push origin feature/#codIssue-nova-funcionalidade`
4. Abra um Pull Request

## 📄 Licença

Este projeto está licenciado sob a [MIT License](LICENSE).
