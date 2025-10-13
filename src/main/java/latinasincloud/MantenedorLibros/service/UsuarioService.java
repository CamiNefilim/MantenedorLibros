package latinasincloud.MantenedorLibros.service;

import latinasincloud.MantenedorLibros.model.Usuario;
import latinasincloud.MantenedorLibros.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario user = usuarioRepository.findByUsuario(username).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        return User.builder()
                .username(user.getUsuario())
                .password(user.getContrasena())
                .roles(user.getPerfil().getNombre())
                .build();
    }
}
