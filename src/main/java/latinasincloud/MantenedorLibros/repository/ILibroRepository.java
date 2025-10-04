package latinasincloud.MantenedorLibros.repository;

import latinasincloud.MantenedorLibros.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ILibroRepository extends JpaRepository<Libro,Integer> {
    // Buscar por el nombre de la categoría (a través de la relación)
    List<Libro> findByCategoria_Nombre(String nombre);
}
