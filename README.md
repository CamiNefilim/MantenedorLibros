# 📚 API REST - Mantenedor de Libros

Este proyecto es una **API REST en Spring Boot** que implementa un mantenedor de libros.  
Los datos se almacenan en una **lista en memoria** (sin base de datos ni JPA).  

Proyecto creado con: Spring Initializr [https://start.spring.io/]

## 🚀 Características
- Crear, listar, actualizar y eliminar libros.
- Manejo de capa `Repository`, `Service` y `Controller`.
- Uso correcto de códigos HTTP.
- En rama master: Datos persistidos en BD PostgresSQL. Script para creación de tablas en `resources/static/script_bd.sql`
- En rama sin_persistencia: Datos persistidos solo en memoria (se reinician al detener la app).

## 🛠️ Tecnologías
- Java 21
- Spring Boot 3.5.6
- Maven
- Postman (para pruebas)

## 📑 Endpoints

1. Listar todos los libros: GET /api/libros
2. Obtener un libro por ID: GET /api/libros/{id}
3. Crear un nuevo libro: POST /api/libros

Ejemplos: Request Body 
```json
{
  "titulo": "El Principito",
  "categoria": "Ficción",
  "anioPublicacion": 1943,
  "autor": "Antoine de Saint-Exupéry"
}

{
    "titulo": "Cien años de soledad",
    "categoria": "Novela",
    "anioPublicacion": 1967,
    "autor": "Gabriel García Márquez"
}

{
    "titulo": "Los juegos del hambre",
    "categoria": "Juvenil",
    "anioPublicacion": 2008,
    "autor": "Suzanne Collins"
}

{
    "titulo": "Carrie",
    "categoria": "Terror",
    "anioPublicacion": 1974,
    "autor": "Stephen King"
}

```

4. Actualizar un libro existente: PUT /api/libros/{id} (Request Body)
5. Eliminar un libro: DELETE /api/libros/{id}

## 🧪 Probar con Postman

Puedes importar la colección de Postman y ejecutar los endpoints:

- Abrir Postman
- Crear nueva request con http://localhost:8080/api/libros
- Probar GET, POST, PUT, DELETE
