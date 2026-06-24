package cl.duoc.usuarios_service;

import cl.duoc.usuarios_service.model.Usuario;
import net.datafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UsuariosTest {


    @Test
    @DisplayName("USR-01 - Verificar que un usuario pueda crearse correctamente")
    public void crearUsuario() {

        // ARRANGE

        // DataFaker
        Faker faker = new Faker();

        Usuario usuario = new Usuario(
                1,
                faker.name().firstName(),
                faker.name().lastName(),
                faker.name().lastName()
        );

        // ACT
        System.out.println(usuario);

        // ASSERT
        assertNotNull(usuario);

        assertNotNull(
                usuario.getNombre()
        );

        assertNotNull(
                usuario.getApellidoPaterno()
        );

        assertNotNull(
                usuario.getApellidoMaterno()
        );
    }

    @Test
    @DisplayName("USR-02 - Verificar que el nombre del usuario sea válido")
    public void nombreUsuarioValido() {

        // ARRANGE

        // DataFaker
        Faker faker = new Faker();

        Usuario usuario = new Usuario(
                1,
                faker.name().firstName(),
                "Gonzalez",
                "Perez"
        );

        // ACT
        System.out.println(usuario);

        // ASSERT
        assertFalse(
                usuario.getNombre().isBlank()
        );

        assertTrue(
                usuario.getNombre().length() <= 100
        );
    }

    @Test
    @DisplayName("USR-03 - Verificar que el apellido paterno sea válido")
    public void apellidoPaternoValido() {

        // ARRANGE

        // DataFaker
        Faker faker = new Faker();

        Usuario usuario = new Usuario(
                1,
                "Juan",
                faker.name().lastName(),
                "Perez"
        );

        // ACT
        System.out.println(usuario);

        // ASSERT
        assertFalse(
                usuario.getApellidoPaterno().isBlank()
        );

        assertTrue(
                usuario.getApellidoPaterno().length() <= 100
        );
    }

    @Test
    @DisplayName("USR-04 - Verificar que el apellido materno sea válido")
    public void apellidoMaternoValido() {

        // ARRANGE

        // DataFaker
        Faker faker = new Faker();

        Usuario usuario = new Usuario(
                1,
                "Juan",
                "Gonzalez",
                faker.name().lastName()
        );

        // ACT
        System.out.println(usuario);

        // ASSERT
        assertFalse(
                usuario.getApellidoMaterno().isBlank()
        );

        assertTrue(
                usuario.getApellidoMaterno().length() <= 100
        );
    }

    @Test
    @DisplayName("USR-05 - Verificar que un usuario tenga nombre completo")
    public void usuarioTieneNombreCompleto() {

        // ARRANGE
        Usuario usuario = new Usuario(
                1,
                "Juan",
                "Gonzalez",
                "Perez"
        );

        // ACT
        String nombreCompleto =
                usuario.getNombre() + " "
                        + usuario.getApellidoPaterno() + " "
                        + usuario.getApellidoMaterno();

        System.out.println(nombreCompleto);

        // ASSERT
        assertEquals(
                "Juan Gonzalez Perez",
                nombreCompleto
        );
    }


}
