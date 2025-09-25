package latinasincloud.MantenedorLibros.controller;

import latinasincloud.MantenedorLibros.service.LibroService;
import latinasincloud.MantenedorLibros.model.Libro;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;


@RestController
@RequestMapping("/api/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    // Crear libro (POST)
    @PostMapping
    public ResponseEntity<Libro> crearLibro(@RequestBody Libro libro) {
        Libro nuevoLibro = libroService.crearLibro(libro);
        return ResponseEntity.status(201).body(nuevoLibro);
    }

    // Listar todos (GET)
    @GetMapping
    public ResponseEntity<List<Libro>> listarLibros() {
        return ResponseEntity.ok(libroService.listarLibros());
    }

    // Obtener libro por id (GET)
    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerLibro(@PathVariable int id) {
        Libro libro = libroService.obtenerLibro(id);
        if (libro != null) {
            return ResponseEntity.ok(libro);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Actualizar libro (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizarLibro(@PathVariable int id, @RequestBody Libro libroAc) {
        Libro actualizado = libroService.actualizarLibro(id, libroAc);
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar libro (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable int id) {
        boolean eliminado = libroService.eliminarLibro(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


//*
// [
//    {
//        "id": 1,
//        "titulo": "Cien años de soledad",
//        "categoria": "Novela",
//        "anioPublicacion": 1967,
//        "autor": "Gabriel García Márquez"
//    },
//    {
//        "id": 2,
//        "titulo": "Los juegos del hambre",
//        "categoria": "Juvenil",
//        "anioPublicacion": 2008,
//        "autor": "Suzanne Collins"
//    },
//    {
//        "id": 3,
//        "titulo": "Carrie",
//        "categoria": "Terror",
//        "anioPublicacion": 1974,
//        "autor": "Stephen King"
//    }
//]
// *//