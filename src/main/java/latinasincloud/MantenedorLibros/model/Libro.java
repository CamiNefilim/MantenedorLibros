package latinasincloud.MantenedorLibros.model;

import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false) // FK hacia Categoria
    private Categoria categoria;

    private int anioPublicacion;
    private String autor;

    public Libro(){}

    public Libro(int id, String titulo, Categoria categoria, int anioPublicacion, String autor) {
        this.id = id;
        this.titulo = titulo;
        this.categoria = categoria;
        this.anioPublicacion = anioPublicacion;
        this.autor = autor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
