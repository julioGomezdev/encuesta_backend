# Encuesta Backend

Este proyecto es un backend en Java usando Spring Boot, H2 Database y Apache Camel para manejar encuestas musicales. A continuación, se detallan los pasos para la configuración, comandos útiles y ejemplos de llamadas a la API.

## Requisitos

- Java 8 o superior
- Maven
- IDE como IntelliJ o Eclipse (opcional)

## Instalación

### 1. Clonar el repositorio

git clone https://github.com/julioGomezdev/encuesta_backend.git
cd encuesta_backend

### 2. Compilar el proyecto
mvn clean install

### 3. Ejecutar el proyecto
mvn spring-boot:run
Esto iniciará el servidor en http://localhost:8080.

## Crear automáticamente la tabla ENCUESTA
El backend creará la tabla ENCUESTA al iniciar la aplicación si no existe. No es necesario crearla manualmente.

Navega a http://localhost:8080/h2-console.
Ingresa los siguientes detalles:
JDBC URL: jdbc:h2:file:./test
User Name: sa
Password: (deja este campo vacío)


## APIs

### Registrar Encuesta

curl --location 'http://localhost:8080/api/encuestas/registrar' \
--header 'Content-Type: application/json' \
--data-raw '{
"email": "usuario@ejemplo.com",
"estiloMusical": "Rock"
}'

Respuesta exitosa:

{
    "mensaje": "Encuesta registrada exitosamente"
}

### Obtener Resultados

curl --location 'http://localhost:8080/api/encuestas/resultados'

Respuesta exitosa:

{
    "resultados": {
        "Rock": 5,
        "Pop": 3
        }
}

## Pruebas Unitarias
mvn test

## Contacto
Este archivo `README.md` contiene toda la información que necesitas para ejecutar y probar el backend, incluyendo ejemplos de uso de `curl` y comandos útiles.


