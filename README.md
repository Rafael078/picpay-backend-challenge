# 💸 PicPay Backend Challenge

Este é um projeto desenvolvido como solução para o **desafio backend da PicPay**. O objetivo principal é simular uma API de transferência de dinheiro entre usuários, com regras de negócio como autorização externa e notificação. O projeto foi desenvolvido utilizando **Java 17** e **Spring Boot**.

This project was developed as a solution for the **PicPay backend challenge**. The main goal is to simulate a money transfer API between users, including business rules such as external authorization and user notification. The project was built using **Java 17** and **Spring Boot**.

---

## 🚀 Tecnologias | Technologies

- Java 17  
- Spring Boot  
- Spring Data JPA  
- H2 Database (banco de dados em memória)  
- Maven  
- REST API  
- JUnit (para testes unitários)

---

## 📌 Funcionalidades | Features

- Cadastro de usuários (comum e lojista)  
- Consulta de saldo  
- Transferência entre usuários  
- Autorização da transação via serviço externo  
- Notificação ao usuário recebedor após a transação  
- Validações: saldo suficiente, tipo de usuário, etc.  
- Testes unitários básicos

---

## 🧪 Como executar o projeto | How to run the project

### Pré-requisitos | Prerequisites

- Java 17  
- Maven

### Passos | Steps

```bash
# Clone o repositório / Clone the repository
git clone https://github.com/Rafael078/picpay-backend-challenge.git
cd picpay-backend-challenge

# Compile e execute a aplicação / Build and run the application
mvn spring-boot:run
