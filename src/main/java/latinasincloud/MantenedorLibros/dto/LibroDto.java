package latinasincloud.MantenedorLibros.dto;

import latinasincloud.MantenedorLibros.model.Categoria;
import latinasincloud.MantenedorLibros.model.Libro;

public class LibroDto {
    private int id;
    private String titulo;
    private String categoria;
    private String autor;
    private int anioPublicacion;

    public LibroDto(int id, String titulo, String categoriaNombre, String autor, int anioPublicacion) {
        this.id = id;
        this.titulo = titulo;
        this.categoria = categoriaNombre;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getAutor() {
        return autor;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

}
