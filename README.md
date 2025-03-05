# Ejerc008

## Descripción del Proyecto

Ejerc008 es una aplicación Spring Boot que gestiona dos entidades: **Persona** y **Provincia**. La aplicación proporciona una API RESTful para realizar operaciones CRUD en estas entidades.

## Entidades

### Persona
- Representa a una persona con los siguientes campos:
  - `id`: Identificador único de la persona.
  - `nombre`: Nombre de la persona.
  - `apellido`: Apellido de la persona.
  - `email`: Correo electrónico de la persona.
  - `provincia`: Provincia asociada.
  - `usuario`: Usuario asociado.

### Provincia
- Representa una provincia con los siguientes campos:
  - `id`: Identificador único de la provincia.
  - `nombre`: Nombre de la provincia.
  - `codigo`: Código de la provincia.
  - `descripcion`: Descripción de la provincia.

## Ejemplo de JSON para Crear una Persona

```json
{
  "nombre": "Juan",
  "apellido": "Perez",
  "email": "juan.perez@example.com",
  "provincia": { 
    "id": 1
  },
  "usuario": {
    "username": "juan",
    "password": "password123"
  }
}
Nota: La provincia no se modificará desde persona y se asignará la provincia por el ID marcado, ignorando los demás datos de provincia.

Ejecutar la Aplicación
Para ejecutar la aplicación, utiliza el siguiente comando:
mvn spring-boot:run
Usar Swagger
Swagger está habilitado en esta aplicación para proporcionar una documentación interactiva de la API y una interfaz de prueba.

Acceder a Swagger UI
Una vez que la aplicación esté en ejecución, puedes acceder a Swagger UI en la siguiente URL:

http://localhost:8080/swagger-ui.html

Usar Swagger UI
Abrir Swagger UI: Navega a http://localhost:8080/swagger-ui.html en tu navegador web.

Ejemplos de Solicitudes API
Crear una Persona
Endpoint: POST /api/personas
Cuerpo de la Solicitud:
{
  "nombre": "Juan",
  "apellido": "Perez",
  "email": "juan.perez@example.com",
  "provincia": { 
    "id": 1
  },
  "usuario": {
    "username": "juan",
    "password": "password123"
  }
}
Obtener Todas las Personas
Endpoint: GET /api/personas
Obtener una Persona por ID
Endpoint: GET /api/personas/{id}
Parámetro de Ruta: id - El ID de la persona a obtener.
Actualizar una Persona
Endpoint: PUT /api/personas/{id}
Parámetro de Ruta: id - El ID de la persona a actualizar.
Cuerpo de la Solicitud:
{
  "nombre": "Carlos",
  "apellido": "Gomez",
  "email": "carlos.gomez@example.com",
  "provincia": { 
    "id": 2
  },
  "usuario": {
    "username": "carlos",
    "password": "newpassword123"
  }
}

Eliminar una Persona
Endpoint: DELETE /api/personas/{id}
Parámetro de Ruta: id - El ID de la persona a eliminar.
El cual eliminara tanto persona como usuario asociado.

Ejecutar Pruebas
Para ejecutar las pruebas, utiliza el siguiente comando:

mvn test
