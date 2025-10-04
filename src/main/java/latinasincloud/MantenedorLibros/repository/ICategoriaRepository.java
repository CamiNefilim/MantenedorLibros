package latinasincloud.MantenedorLibros.repository;

import latinasincloud.MantenedorLibros.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ICategoriaRepository  extends JpaRepository<Categoria,Integer> {
    Optional<Categoria> findByNombre(String nombre);
}
