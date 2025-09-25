# 📚 API REST - Mantenedor de Libros

Este proyecto es una **API REST en Spring Boot** que implementa un mantenedor de libros.  
Los datos se almacenan en una **lista en memoria** (sin base de datos ni JPA).  

Proyecto creado con: (Spring Initializr)[https://start.spring.io/]

## 🚀 Características
- Crear, listar, actualizar y eliminar libros.
- Manejo de capa `Service` y `Controller`.
- Uso correcto de códigos HTTP.
- Datos persistidos solo en memoria (se reinician al detener la app).

## 🛠️ Tecnologías
- Java 17+
- Spring Boot 3.x
- Maven
- Postman (para pruebas)

## 📦 Instalación
1. Clonar el repositorio:
   ```bash
   git clone https://github.com/tu-usuario/mantenedor-libros.git
   cd mantenedor-libros
   ```

2. Compilar e iniciar el proyecto:

  ```bash
  mvn spring-boot:run
  ```

3. La API estará disponible en: http://localhost:8080/api/libros


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
