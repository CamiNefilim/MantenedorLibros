package latinasincloud.MantenedorLibros.service;

import latinasincloud.MantenedorLibros.dto.LibroDto;
import latinasincloud.MantenedorLibros.model.Categoria;
import latinasincloud.MantenedorLibros.model.Libro;
import latinasincloud.MantenedorLibros.repository.ILibroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;


public class LibroServiceTest {

    /*
       Un mock es un objeto falso que simula el comportamiento de un objeto real.
       Se usa para aislar la prueba y no depender de dependencias externas.
    */
    @Mock
    private ILibroRepository libroRepository;

    @Mock
    private CategoriaService categoriaService;

    /*
       @InjectMocks es una anotación de Mockito que inyecta automáticamente
       los mocks en el objeto que quieres probar.
    */
    @InjectMocks
    private LibroService libroService;

    private LibroDto libroDto;
    private Libro libro;
    private Categoria categoria;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks

        categoria = new Categoria();
        categoria.setId(1);
        categoria.setNombre("Ficción");

        libroDto = new LibroDto(1, "El Quijote", "Ficción", "Miguel de Cervantes", 1605);

        libro = new Libro();
        libro.setId(1);
        libro.setTitulo("El Quijote");
        libro.setAutor("Miguel de Cervantes");
        libro.setAnioPublicacion(1605);
        libro.setCategoria(categoria);
    }

    //@Test: Le indica a JUnit que este método es una prueba que debe ejecutarse.
    @Test
    void testCrearLibroExitosamente() {
        /*
            Sección "Arrange" (Preparación): Define el comportamiento de los mocks:
            "Cuando se llame a obtenerCategoriaPorNombre("Ficción"), devuelve el objeto categoria"
            "Cuando se llame a save() con cualquier Libro, devuelve el objeto libro"
        */
        Mockito.when(categoriaService.obtenerCategoriaPorNombre("Ficción")).thenReturn(categoria);
        Mockito.when(libroRepository.save(Mockito.any(Libro.class))).thenReturn(libro);

        /*
            Sección "Act" (Acción): Ejecuta el método que quieres probar.
            El servicio usará los mocks que configuraste.
        */
        LibroDto resultado = libroService.crearLibro(libroDto);

        /*
            Sección "Assert" (Verificación): Verifica que el resultado sea correcto:
            assertNotNull() confirma que el resultado no es nulo
            assertEquals() compara valores esperados vs. reales
        */
        assertNotNull(resultado);
        assertEquals("El Quijote", resultado.getTitulo());
        assertEquals("Miguel de Cervantes", resultado.getAutor());
        assertEquals(1605, resultado.getAnioPublicacion());

        /*
            Sección "Verify" de Mocks: Confirma que los mocks fueron llamados correctamente:
            - El servicio de categoría fue llamado exactamente 1 vez con "Ficción"
            - El repositorio fue llamado exactamente 1 vez con un Libro
         */
        Mockito.verify(categoriaService, Mockito.times(1)).obtenerCategoriaPorNombre("Ficción");
        Mockito.verify(libroRepository, Mockito.times(1)).save(Mockito.any(Libro.class));

        /*
        **Flujo completo:**
            1. ARRANGE: Configuro que mocks devuelvan
            2. ACT: Ejecuto el método real. El servicio usará los mocks que configuraste.
            3. ASSERT: Verifico que el resultado es correcto
            4. VERIFY: Confirmo que los mocks se usaron correctamente
        */
    }

    @Test
    void testCrearLibroCategoriaNoEncontrada() {
        Mockito.when(categoriaService.obtenerCategoriaPorNombre("Ficción")).thenReturn(null);
        /*
            assertThrows verifica que un método lanze una excepción específica.
            Comprueba que cuando llames a crearLibro(libroDto), se lance una RuntimeException.
        */
        assertThrows(RuntimeException.class, () -> libroService.crearLibro(libroDto));
        Mockito.verify(categoriaService, Mockito.times(1)).obtenerCategoriaPorNombre("Ficción");
        Mockito.verify(libroRepository, Mockito.never()).save(Mockito.any(Libro.class));
    }

    @Test
    void testListarLibrosExitosamente() {
        // Arrange
        Libro libro2 = new Libro();
        libro2.setId(2);
        libro2.setTitulo("Cien años de soledad");
        libro2.setAutor("Gabriel García Márquez");
        libro2.setAnioPublicacion(1967);
        libro2.setCategoria(categoria);

        List<Libro> libros = Arrays.asList(libro, libro2);
        Mockito.when(libroRepository.findAll()).thenReturn(libros);

        // Act
        List<LibroDto> resultado = libroService.listarLibros();

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("El Quijote", resultado.get(0).getTitulo());
        assertEquals("Cien años de soledad", resultado.get(1).getTitulo());
        Mockito.verify(libroRepository, Mockito.times(1)).findAll();
    }

    @Test
    void testListarLibrosVacio() {
        // Arrange
        Mockito.when(libroRepository.findAll()).thenReturn(Arrays.asList());

        // Act
        List<LibroDto> resultado = libroService.listarLibros();

        // Assert
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        Mockito.verify(libroRepository, Mockito.times(1)).findAll();
    }

    @Test
    void testObtenerLibroPorIdExitosamente() {
        // Arrange
        Mockito.when(libroRepository.findById(1)).thenReturn(Optional.of(libro));

        // Act
        LibroDto resultado = libroService.obtenerLibroDto(1);

        // Assert
        assertNotNull(resultado);
        assertEquals("El Quijote", resultado.getTitulo());
        assertEquals(1605, resultado.getAnioPublicacion());
        Mockito.verify(libroRepository, Mockito.times(1)).findById(1);
    }

    @Test
    void testObtenerLibroPorIdNoEncontrado() {
        // Arrange
        Mockito.when(libroRepository.findById(999)).thenReturn(Optional.empty());

        // Act
        LibroDto resultado = libroService.obtenerLibroDto(999);

        // Assert
        assertNull(resultado);
        Mockito.verify(libroRepository, Mockito.times(1)).findById(999);
    }

    @Test
    void testObtenerLibrosPorCategoria() {
        // Arrange
        List<Libro> libros = Arrays.asList(libro);
        Mockito.when(libroRepository.findByCategoria_Nombre("Ficción")).thenReturn(libros);

        // Act
        List<LibroDto> resultado = libroService.obtenerLibrosCategoria("Ficción");

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("El Quijote", resultado.get(0).getTitulo());
        Mockito.verify(libroRepository, Mockito.times(1)).findByCategoria_Nombre("Ficción");
    }

    @Test
    void testActualizarLibroExitosamente() {
        // Arrange
        LibroDto libroActualizado = new LibroDto(1, "El Quijote Actualizado", "Ficción", "Cervantes", 1605);
        Mockito.when(libroRepository.findById(1)).thenReturn(Optional.of(libro));
        Mockito.when(libroRepository.save(Mockito.any(Libro.class))).thenReturn(libro);

        // Act
        LibroDto resultado = libroService.actualizarLibro(1, libroActualizado);

        // Assert
        assertNotNull(resultado);
        Mockito.verify(libroRepository, Mockito.times(1)).findById(1);
        Mockito.verify(libroRepository, Mockito.times(1)).save(Mockito.any(Libro.class));
    }

    @Test
    void testActualizarLibroNoEncontrado() {
        // Arrange
        Mockito.when(libroRepository.findById(999)).thenReturn(Optional.empty());

        // Act
        LibroDto resultado = libroService.actualizarLibro(999, libroDto);

        // Assert
        assertNull(resultado);
        Mockito.verify(libroRepository, Mockito.times(1)).findById(999);
        Mockito.verify(libroRepository, Mockito.never()).save(Mockito.any(Libro.class));
    }

    @Test
    void testActualizarLibroCamposVacios() {
        // Arrange
        LibroDto libroConCamposVacios = new LibroDto(1, "", "", "", 0);
        Mockito.when(libroRepository.findById(1)).thenReturn(Optional.of(libro));
        Mockito.when(libroRepository.save(Mockito.any(Libro.class))).thenReturn(libro);

        // Act
        LibroDto resultado = libroService.actualizarLibro(1, libroConCamposVacios);

        // Assert
        assertNotNull(resultado);
        assertEquals("El Quijote", resultado.getTitulo()); // Mantiene valores originales
        assertEquals("Miguel de Cervantes", resultado.getAutor());
        Mockito.verify(libroRepository, Mockito.times(1)).save(Mockito.any(Libro.class));
    }

    @Test
    void testEliminarLibroExitosamente() {
        // Arrange
        Mockito.when(libroRepository.findById(1)).thenReturn(Optional.of(libro));

        // Act
        boolean resultado = libroService.eliminarLibro(1);

        // Assert
        assertTrue(resultado);
        Mockito.verify(libroRepository, Mockito.times(1)).findById(1);
        Mockito.verify(libroRepository, Mockito.times(1)).delete(libro);
    }

    @Test
    void testEliminarLibroNoEncontrado() {
        // Arrange
        Mockito.when(libroRepository.findById(999)).thenReturn(Optional.empty());

        // Act
        boolean resultado = libroService.eliminarLibro(999);

        // Assert
        assertFalse(resultado);
        Mockito.verify(libroRepository, Mockito.times(1)).findById(999);
        Mockito.verify(libroRepository, Mockito.never()).delete(Mockito.any(Libro.class));
    }

}
