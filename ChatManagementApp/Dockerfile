FROM eclipse-temurin:21-jre-alpine AS build
WORKDIR /chatmanageapp
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /chatmanageapp
COPY target/ChatManagementApp-0.0.1-SNAPSHOT.jar ChatManagementApp.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "ChatManagementApp.jar"]
