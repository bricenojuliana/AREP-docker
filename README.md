
# Message Log Service with Load Balancer

## Descripción

Este proyecto es una aplicación de registro de mensajes que incluye un balanceador de carga. El balanceador de carga distribuye las solicitudes entre múltiples instancias del servicio de mensajes utilizando un algoritmo de Round Robin. El servicio de mensajes almacena los mensajes en una base de datos MongoDB y permite la visualización de los últimos 10 mensajes almacenados.

## Estructura del Proyecto

- **Load Balancer**: Balancea las solicitudes entrantes entre múltiples instancias del servicio de mensajes.
- **Log Service**: Servicio de mensajes que almacena y recupera mensajes de una base de datos MongoDB.
- **MongoDB**: Base de datos NoSQL que almacena los mensajes.

## Requisitos

- Docker
- Docker Compose

## Configuración del Entorno

### Docker Compose

El archivo `docker-compose.yml` define los siguientes servicios:

- `mongodb`: Instancia de MongoDB para almacenar los mensajes.
- `logservice1`, `logservice2`, `logservice3`: Tres instancias del servicio de mensajes.
- `webapp`: Aplicación web para enviar y visualizar mensajes.

### Dockerfile para el Servicio de Mensajes

Asegúrate de tener los siguientes archivos `Dockerfile` para el servicio de mensajes:

**Dockerfile-logservice**

```Dockerfile
# Usar una imagen base de Java
FROM openjdk:17

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /usrapp/bin

ENV PORT=8080

# Copiar el archivo JAR generado por Maven al contenedor
COPY target/*.jar /usrapp/bin/app.jar

# Comando para ejecutar el servicio
CMD ["java", "-jar", "app.jar"]
```

### Dockerfile para la Aplicación Web

**Dockerfile-web**

```Dockerfile
# Usar una imagen base de Node.js
FROM node:18

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /usrapp

# Copiar los archivos del proyecto al contenedor
COPY . .

# Instalar dependencias
RUN npm install

# Exponer el puerto en el que la aplicación escuchará
EXPOSE 8080

# Comando para iniciar la aplicación
CMD ["npm", "start"]
```

## Construcción y Ejecución

1. **Construir y Ejecutar los Contenedores**:

   En el directorio raíz del proyecto, ejecuta:

   ```bash
   docker-compose up --build
   ```

2. **Acceder a la Aplicación Web**:

   La aplicación web estará disponible en `http://localhost:8080`.

## Uso

- **Enviar Mensajes**:

  Usa el formulario en la interfaz web para enviar mensajes. Los mensajes se enviarán al balanceador de carga, que distribuirá las solicitudes entre las instancias del servicio de mensajes.

- **Ver Mensajes**:

  Los últimos 10 mensajes se mostrarán en una tabla en la interfaz web, con la fecha y el texto del mensaje.

## Endpoints del Balanceador de Carga

- **POST /messages**: Envía un mensaje al servicio de mensajes.
- **GET /messages/last10**: Obtiene los últimos 10 mensajes almacenados.

## Archivos del Proyecto

- **`src/main/java/edu/escuelaing/arep/logservice/`**: Código fuente del servicio de mensajes.
- **`src/main/java/edu/escuelaing/arep/logservice/loadbalancer/`**: Código fuente del balanceador de carga.
- **`src/main/resources/application.properties`**: Configuración del servicio.
- **`Dockerfile-logservice`**: Dockerfile para el servicio de mensajes.
- **`Dockerfile-web`**: Dockerfile para la aplicación web.
- **`docker-compose.yml`**: Configuración de Docker Compose.

## Contribuciones

Las contribuciones son bienvenidas. Por favor, abre un "issue" o "pull request" para sugerir mejoras o reportar problemas.

## Licencia

Este proyecto está licenciado bajo la [MIT License](LICENSE).

```

Este archivo `README.md` proporciona una visión general completa de tu proyecto, instrucciones para construir y ejecutar los contenedores, así como detalles sobre los archivos y la estructura del proyecto. Asegúrate de ajustar los detalles según las necesidades específicas de tu aplicación.
