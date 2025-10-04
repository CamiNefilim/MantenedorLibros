package latinasincloud.MantenedorLibros.service;

import latinasincloud.MantenedorLibros.dto.LibroDto;
import latinasincloud.MantenedorLibros.model.Categoria;
import latinasincloud.MantenedorLibros.model.Libro;
import latinasincloud.MantenedorLibros.repository.ILibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LibroService {

    @Autowired
    private ILibroRepository libroRepository;

    @Autowired
    private CategoriaService categoriaService;

    private LibroDto toDto(Libro libro) {
        return new LibroDto(
                libro.getId(),
                libro.getTitulo(),
                libro.getCategoria().getNombre(),
                libro.getAutor(),
                libro.getAnioPublicacion()
        );
    }

    private Libro toEntity(LibroDto libroDto, Categoria categoria) {
        Libro libro = new Libro();
        libro.setTitulo(libroDto.getTitulo());
        libro.setAutor(libroDto.getAutor());
        libro.setAnioPublicacion(libroDto.getAnioPublicacion());
        libro.setCategoria(categoria);
        return libro;
    }

    // Crear libro
    public LibroDto crearLibro(LibroDto libroDto) {
        Categoria categoria = categoriaService.obtenerCategoriaPorNombre(libroDto.getCategoria());
        if (categoria == null) {
            throw new RuntimeException("Categoría no encontrada");
        }

        Libro libro = toEntity(libroDto, categoria);
        Libro guardado = libroRepository.save(libro);

        return toDto(guardado);
    }

    // Listar todos
    public List<LibroDto> listarLibros() {
        return libroRepository.findAll().stream().map(this::toDto).toList();
    }

    // Buscar por id
    private Libro obtenerLibro(int id) {
        return libroRepository.findById(id).orElse(null);
    }

    public LibroDto obtenerLibroDto(int id) {
        Libro libro = obtenerLibro(id);
        return libro != null ? toDto(libro) : null;
    }

    // Buscar por categoria
    public List<LibroDto> obtenerLibrosCategoria(String categoria) {
        return libroRepository.findByCategoria_Nombre(categoria).stream().map(this::toDto).toList();
    }

    // Actualizar
    public LibroDto actualizarLibro(int id, LibroDto libroDto) {
        Libro libro = obtenerLibro(id);
        if (libro == null) return null;

        libro.setTitulo(libroDto.getTitulo().isEmpty() ? libro.getTitulo() : libroDto.getTitulo());
        libro.setAutor(libroDto.getAutor().isEmpty() ? libro.getAutor() : libroDto.getAutor());
        libro.setAnioPublicacion(libroDto.getAnioPublicacion() == 0 ? libro.getAnioPublicacion() : libroDto.getAnioPublicacion());

        if (libroDto.getCategoria() != null) {
            Categoria categoria = categoriaService.obtenerCategoriaPorNombre(libroDto.getCategoria());
            if (categoria != null) libro.setCategoria(categoria);
        }

        libroRepository.save(libro);
        return toDto(libro);
    }

    // Eliminar
    public boolean eliminarLibro(int id) {
        Libro libro = obtenerLibro(id);
        if (libro != null) {
            libroRepository.delete(libro);
            return true;
        }
        return false;
    }
}
