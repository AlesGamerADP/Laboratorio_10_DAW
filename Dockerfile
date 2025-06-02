# Etapa 1: Build con Maven y JDK 17
FROM maven:3.8.7-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final para correr la app
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/Laboratorio_10-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "app.jar"]
