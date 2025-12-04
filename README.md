# Chat App Management Backend

A scalable, Backend for a real-time chat Management application built with Java 21, Spring Boot, PostgreSQL, and Docker. Designed for modularity, reliability, and easy deployment.

## 🚀 Features

- RESTfull APIs for user and message management
- PostgreSQL with schema-based isolation
- Dockerized for consistent local and cloud environments
- Secure authentication with API Header Key

## 🛠️ Tech Stack

- Java 21
- Spring Boot 3.2.4
- Spring Cloud 2023.0.1
- Consul Disovery Client
- PostgreSQL 17
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

### 2.2 Running the Entier App Including Consul Client/Postgres DB server in Docker

```bash
cd Chat-Management-App

docker-compose up --build

```

## API Documentation

-- Swagger API documentation is implemented and accessible by below URL

http://host:port/swagger-ui/index.html#

### API Information

-/create-new-session --> this API will create new session by taking optional session id value, Session must be created for any user to update the messages for the user

-/update-msg-chat-session --> This API will update messages for the user for particular session id , Session Id is mandatory to update the messages,
								Messages can be single or bulk, API will take JSON array for messages and can be used for bulk message updates as well,
								

-/delete-chat-session --> This API will delete the particular session and messages for a USER and session id, Session Id is mandatory to delete complete session


-/retrieve-chat-session --> API for retriving messages in pages, Pagination is enabled for this API.

-/update-session-messages --> This API will be called when updated like session name/ tagging chat favorite or not, Session Id is mandatory for this API as well.						
