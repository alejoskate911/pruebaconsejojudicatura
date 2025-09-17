# Etapa 1: Build (con Maven)
FROM maven:3.9.3-eclipse-temurin-17 AS build
WORKDIR /app

# Copiamos pom y código fuente
COPY pom.xml .
COPY src ./src

# Construimos el proyecto y generamos el JAR
RUN mvn clean package -DskipTests

# Etapa 2: Run (solo JDK)
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copiamos el JAR desde la etapa de build
COPY --from=build /app/target/*.jar app.jar

# Exponemos el puerto de la aplicación
EXPOSE 8080

# Comando para ejecutar la app
ENTRYPOINT ["java", "-jar", "app.jar"]

