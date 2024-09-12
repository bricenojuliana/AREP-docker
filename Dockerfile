# Usar una imagen base ligera de Java 17
FROM openjdk:17-jdk-slim

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /usrapp/bin

# Establecer el puerto por defecto como variable de entorno
ENV PORT=8080

# Copiar los archivos compilados de tu aplicación al contenedor
COPY target/classes /usrapp/bin/classes
COPY target/dependency /usrapp/bin/dependency

# Exponer el puerto (opcional si ya lo manejas en docker-compose)
EXPOSE 8080

# Comando para ejecutar el servicio
CMD ["java", "-cp", "./classes:./dependency/*", "edu.escuelaing.arep.logservice.LogServiceApplication"]
