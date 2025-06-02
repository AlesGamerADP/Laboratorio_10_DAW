# Imagen base con JDK 17
FROM eclipse-temurin:17-jdk-alpine

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR generado por Spring Boot
COPY target/Laboratorio_10-0.0.1-SNAPSHOT.jar app.jar



# Exponer el puerto que tu aplicación usa (8086)
EXPOSE 8086

# Comando para ejecutar tu aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
