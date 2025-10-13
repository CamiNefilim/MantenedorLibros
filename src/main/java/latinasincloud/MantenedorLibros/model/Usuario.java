package latinasincloud.MantenedorLibros.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String usuario;
    private String contrasena;

    @ManyToOne
    @JoinColumn(name = "perfil_id", nullable = false) // FK hacia Perfil
    private Perfil perfil;

    public Usuario() {
    }

    public Usuario(String usuario, String contrasena, Perfil perfil) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.perfil = perfil;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public Perfil getPerfil() {
        return perfil;
    }
}
