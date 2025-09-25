package latinasincloud.MantenedorLibros.service;

import latinasincloud.MantenedorLibros.model.Libro;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LibroService {

    private final List<Libro> libros = new ArrayList<>();
    private static int contadorId = 1;

    // Crear libro
    public Libro crearLibro(Libro libro) {
        libro.setId(contadorId++);
        libros.add(libro);
        return libro;
    }

    // Listar todos
    public List<Libro> listarLibros() {
        return libros;
    }

    // Buscar por id
    public Libro obtenerLibro(int id) {
        return libros.stream().filter(l -> l.getId() == id).findFirst().orElse(null);
    }

    // Actualizar
    public Libro actualizarLibro(int id, Libro libroAc) {
        Libro libro = obtenerLibro(id);
        if (libro != null) {
            libro.setTitulo(libroAc.getTitulo().isEmpty() ? libro.getTitulo() : libroAc.getTitulo());
            libro.setCategoria(libroAc.getCategoria().isEmpty() ? libro.getCategoria() : libroAc.getCategoria());
            libro.setAnioPublicacion(libroAc.getAnioPublicacion() == 0 ? libro.getAnioPublicacion() : libroAc.getAnioPublicacion());
            libro.setAutor(libroAc.getAutor().isEmpty() ? libro.getAutor() : libroAc.getAutor());
            return libro;
        }
        return null;
    }

    // Eliminar
    public boolean eliminarLibro(int id) {
        return libros.removeIf(libro -> libro.getId() == id);
    }
}
