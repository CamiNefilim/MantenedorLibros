package latinasincloud.MantenedorLibros.controller;

import latinasincloud.MantenedorLibros.dto.LibroDto;
import latinasincloud.MantenedorLibros.service.LibroService;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import java.util.List;


@RestController
@RequestMapping("/api/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    // Crear libro (POST)
    @PostMapping
    public ResponseEntity<?> crearLibro(@RequestBody LibroDto libro) {
        try {
            LibroDto nuevoLibro = libroService.crearLibro(libro);
            return ResponseEntity.status(201).body(nuevoLibro);
        }catch (RuntimeException ex){
            return ResponseEntity.status(400).body(ex.getMessage());
        }
    }

    // Listar todos (GET)
    @GetMapping
    public ResponseEntity<List<LibroDto>> listarLibros() {
        return ResponseEntity.ok(libroService.listarLibros());
    }

    // Obtener libro por id (GET)
    @GetMapping("/{id}")
    public ResponseEntity<LibroDto> obtenerLibro(@PathVariable int id) {
        LibroDto libro = libroService.obtenerLibroDto(id);
        if (libro != null) {
            return ResponseEntity.ok(libro);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Obtener libro por categoria (GET)
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<LibroDto>> obtenerLibrosCategoria(@PathVariable String categoria) {
        return ResponseEntity.ok(libroService.obtenerLibrosCategoria(categoria));
    }

    // Actualizar libro (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<LibroDto> actualizarLibro(@PathVariable int id, @RequestBody LibroDto libroAc) {
        LibroDto actualizado = libroService.actualizarLibro(id, libroAc);
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