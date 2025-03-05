# README.md

# Ejerc008

## Project Overview

Ejerc008 is a Spring Boot application that manages two entities: **Persona** and **Provincia**. The application provides a RESTful API for performing CRUD operations on these entities.

## Entities

### Persona
- Represents a person with the following fields:
  - `id`: Unique identifier for the person.
  - `nombre`: Name of the person.
  - `edad`: Age of the person.
  - `provinciaId`: Identifier for the associated province.

### Provincia
- Represents a province with the following fields:
  - `id`: Unique identifier for the province.
  - `nombre`: Name of the province.

## API Endpoints

### Persona Endpoints
- **Create Persona**: `POST /api/personas`
- **Get All Personas**: `GET /api/personas`
- **Get Persona by ID**: `GET /api/personas/{id}`
- **Update Persona**: `PUT /api/personas/{id}`
- **Delete Persona**: `DELETE /api/personas/{id}`

### Provincia Endpoints
- **Create Provincia**: `POST /api/provincias`
- **Get All Provincias**: `GET /api/provincias`
- **Get Provincia by ID**: `GET /api/provincias/{id}`
- **Update Provincia**: `PUT /api/provincias/{id}`

## Setup Instructions

1. Clone the repository:
   ```
   git clone <repository-url>
   ```

2. Navigate to the project directory:
   ```
   cd ejerc008
   ```

3. Build the project using Maven:
   ```
   ./mvnw clean install
   ```

4. Run the application:
   ```
   ./mvnw spring-boot:run
   ```

5. Access the API at `http://localhost:8080/api`.

## Usage

You can use tools like Postman or curl to interact with the API endpoints. Make sure to set the appropriate HTTP methods and headers as required.

## License

This project is licensed under the MIT License.