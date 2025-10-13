package latinasincloud.MantenedorLibros.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import latinasincloud.MantenedorLibros.dto.LibroDto;
import latinasincloud.MantenedorLibros.service.LibroService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
@Tag(name = "Libros", description = "Administrador de libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    // Listar todos (GET)
    @GetMapping
    @Operation(summary = "Listar libros", description = "Muestra todos los libros")
    public ResponseEntity<List<LibroDto>> listarLibros() {
        return ResponseEntity.ok(libroService.listarLibros());
    }

    // Obtener libro por id (GET)
    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un libro", description = "Devuelve un libro por su ID")
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
    @Operation(summary = "Listar libros por categoría", description = "Entrega los libros de una categoría")
    public ResponseEntity<List<LibroDto>> obtenerLibrosCategoria(@PathVariable String categoria) {
        return ResponseEntity.ok(libroService.obtenerLibrosCategoria(categoria));
    }

    // Crear libro (POST)
    @PostMapping
    @Operation(summary = "Crea libros", description = "Permite añadir un libro")
    public ResponseEntity<?> crearLibro(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del libro",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = LibroDto.class),
                            examples = @ExampleObject(value = "{\"titulo\":\"Carrie\",\"categoria\":\"Terror\",\"anioPublicacion\":1974,\"autor\":\"Stephen King\"}")
                    )
            )
            @RequestBody LibroDto libro) {
        try {
            LibroDto nuevoLibro = libroService.crearLibro(libro);
            return ResponseEntity.status(201).body(nuevoLibro);
        }catch (RuntimeException ex){
            return ResponseEntity.status(400).body(ex.getMessage());
        }
    }

    // Actualizar libro (PUT)
    @PutMapping("/{id}")
    @Operation(summary = "Actualiza libros", description = "Permite actualizar datos de un libro")
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
    @Operation(summary = "Elimina libros", description = "Permite eliminar un libro por su ID")
    public ResponseEntity<Void> eliminarLibro(@PathVariable int id) {
        boolean eliminado = libroService.eliminarLibro(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}