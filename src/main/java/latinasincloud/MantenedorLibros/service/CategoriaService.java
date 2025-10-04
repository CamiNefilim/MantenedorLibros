package latinasincloud.MantenedorLibros.service;

import latinasincloud.MantenedorLibros.model.Categoria;
import latinasincloud.MantenedorLibros.repository.ICategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService{
    @Autowired
    private ICategoriaRepository categoriaRepository;

    public Categoria obtenerCategoriaPorNombre(String nombre){
        return categoriaRepository.findByNombre(nombre).orElse(null);
    }
}
