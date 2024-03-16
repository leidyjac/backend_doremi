# Usa una imagen base de OpenJDK para Java 17
FROM openjdk:17-jdk-slim

# Define el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR de tu aplicación Spring Boot al directorio de trabajo en el contenedor
COPY target/booking-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto en el que tu aplicación Spring Boot está escuchando dentro del contenedor
EXPOSE 8081

# Define las variables de entorno para la conexión a la base de datos
ENV DB_HOST=mysql
ENV DB_PORT=3306
ENV DB_NAME=doremi_db
ENV DB_USERNAME=root
ENV DB_PASSWORD=toor

# Comando para ejecutar tu aplicación Spring Boot cuando se inicie el contenedor
CMD ["java", "-jar", "/app/app.jar"]