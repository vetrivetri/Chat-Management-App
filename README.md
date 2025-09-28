# Chat App Management Backend

A scalable, Backend for a real-time chat Management application built with Java 21, Spring Boot, PostgreSQL, and Docker. Designed for modularity, reliability, and easy deployment.

## 🚀 Features

- RESTful APIs for user and message management
- PostgreSQL with schema-based isolation
- Dockerized for consistent local and cloud environments
- Secure authentication with JWT

## 🛠️ Tech Stack

- Java 21
- Spring Boot 3.2.4
- Spring Cloud 2023.0.1
- Consul Disovery Client
- PostgreSQL 15
- Docker & Docker Compose
- Maven

## 📦 Setup Instructions

### 1. Clone the Repository
```bash
git clone https://github.com/vetrivetri/Chat-Management-App.git
cd Chat-Management-App
cd ChatManagementParent
```

### 2. Start the Consul Server
```bash
cd /consul/path
consul agent -server -bootstrap-expect=1 -data-dir=consul-data -ui -bind=//IP
```

### 2.1 Running the App In local

``` bash
mvn clean install -DskipTests
cd ChatManagementGateway
mvn spring-boot:Run
cd ChatManagementApp
mvn spring-boot:Run
```

### 2.2 Running the Entier App Including Consul Client/Postgres DB serer in Docker

```bash
cd Chat-Management-App

docker-compose up --build

```
