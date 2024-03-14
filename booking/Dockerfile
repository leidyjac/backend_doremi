# Usa una imagen base de OpenJDK para Java 17
FROM openjdk:17-jdk-slim

# Define el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR de tu aplicación Spring Boot al directorio de trabajo en el contenedor
COPY target/booking-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto en el que tu aplicación Spring Boot está escuchando dentro del contenedor
EXPOSE 8081

# Comando para ejecutar tu aplicación Spring Boot cuando se inicie el contenedor
CMD ["java", "-jar", "/app/app.jar"]