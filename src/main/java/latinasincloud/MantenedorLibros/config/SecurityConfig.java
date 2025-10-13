package latinasincloud.MantenedorLibros.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    /*
    *La anotación @Bean le dice a Spring que el método que la lleva devuelve un objeto que debe ser gestionado por el contenedor de Spring (es decir, se convierte en un bean del contexto de la aplicación).
    *En otras palabras: Spring, el objeto que devuelva este método guárdalo y hazlo disponible para inyectarlo en cualquier parte del proyecto.”
    */

    //Codificador de contraseñas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //Usuarios en memoria
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder.encode("admin123"))
                .roles("ADMIN")
                .build();

        UserDetails user = User.withUsername("user")
                .password(passwordEncoder.encode("user123"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

    //Configuración del filtro de seguridad
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                /*
                * Es una técnica donde un sitio malicioso envía peticiones no autorizadas en nombre de un usuario autenticado, aprovechando que el navegador manda automáticamente las cookies (como la de sesión).
                * Por ejemplo: Si estás logueado en tu banco y visitas un sitio malicioso, este podría enviar un POST a tu banco usando tu cookie de sesión sin que tú lo sepas.
                * En una API REST (especialmente cuando usas tokens o Basic Auth), no hay cookies ni formularios HTML tradicionales.
                */
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers(HttpMethod.GET, "/api/libros/**").hasAnyRole("USER", "ADMIN")
                    .requestMatchers(HttpMethod.POST, "/api/libros/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/libros/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/api/libros/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
            )
            .httpBasic(basic -> {});
        return http.build();
    }
}
