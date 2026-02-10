# Agendador de Horários API

Uma API REST desenvolvida com Spring Boot para o gerenciamento de agendamentos de serviços. O sistema permite criar, listar, atualizar e cancelar agendamentos, incluindo validações de conflito de horários.

## 🚀 Tecnologias Utilizadas

* **Java**
* **Spring Boot** (Web, Data JPA)
* **Lombok**
* **Banco de Dados** (SQL via JPA)

## 📋 Funcionalidades

* **Agendamento de Serviços:** Criação de novos agendamentos com verificação automática de conflitos de horário (slots de 1 hora).
* **Listagem Diária:** Busca de todos os agendamentos de uma data específica.
* **Atualização:** Alteração de dados de um agendamento existente.
* **Cancelamento:** Remoção de agendamentos baseada no cliente e horário.

## 🔌 Endpoints da API

### 1. Criar Agendamento
Cadastra um novo horário. Retorna erro caso o horário já esteja ocupado pelo mesmo serviço.

* **URL:** `/agendamentos`
* **Método:** `POST`
* **Corpo da Requisição (JSON):**
    ```json
    {
      "servico": "Corte de Cabelo",
      "profissional": "João Silva",
      "dataHoraAgendamento": "2023-10-25T14:00:00",
      "cliente": "Maria Souza",
      "telefoneCliente": "11999999999"
    }
    ```

### 2. Listar Agendamentos do Dia
Retorna uma lista de agendamentos para uma data específica.

* **URL:** `/agendamentos`
* **Método:** `GET`
* **Parâmetros (Query):**
    * `dataAgendamento`: Data no formato `AAAA-MM-DD` (ex: `2023-10-25`)

### 3. Atualizar Agendamento
Atualiza os dados de um agendamento existente. É necessário informar o cliente e o horário antigo para localizar o registro.

* **URL:** `/agendamentos`
* **Método:** `PUT`
* **Parâmetros (Query):**
    * `cliente`: Nome do cliente original.
    * `dataHoraAgendamento`: Data/Hora original do agendamento.
* **Corpo da Requisição (JSON):**
    ```json
    {
      "servico": "Corte e Barba",
      "profissional": "João Silva",
      "dataHoraAgendamento": "2023-10-25T15:00:00",
      "cliente": "Maria Souza",
      "telefoneCliente": "11999999999"
    }
    ```

### 4. Deletar Agendamento
Remove um agendamento do sistema.

* **URL:** `/agendamentos`
* **Método:** `DELETE`
* **Parâmetros (Query):**
    * `cliente`: Nome do cliente.
    * `dataHoraAgendamento`: Data e Hora do agendamento (ISO-8601).

## ⚠️ Exceções Tratadas

* **ConflitoDeAgendamento:** Lançada quando tenta-se agendar em um horário já ocupado (considerando intervalo de 1 hora).
* **AtendimentoInesistente:** Lançada ao tentar deletar ou atualizar um agendamento que não foi encontrado no banco de dados.

## 📦 Como Executar

1.  Clone o repositório.
2.  Configure o banco de dados no `application.properties`.
3.  Execute a aplicação via Maven ou através da sua IDE de preferência.
