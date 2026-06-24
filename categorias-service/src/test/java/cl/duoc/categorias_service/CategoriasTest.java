package cl.duoc.categorias_service;

import cl.duoc.categorias_service.model.Categoria;
import cl.duoc.categorias_service.model.CategoriaPersonalizada;
import net.datafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoriasTest {

    @Test
    @DisplayName("CAT-01 - Verificar que una categoría pueda crearse correctamente")
    public void crearCategoria() {

        // ARRANGE
        Categoria categoria = new Categoria(
                1,
                Categoria.NombreCategoria.comida,
                Categoria.TipoCategoria.gasto
        );

        // ACT
        System.out.println(categoria);

        // ASSERT
        assertNotNull(categoria);

        assertEquals(
                Categoria.NombreCategoria.comida,
                categoria.getNombre()
        );

        assertEquals(
                Categoria.TipoCategoria.gasto,
                categoria.getTipo()
        );
    }

    @Test
    @DisplayName("CAT-02 - Verificar que una categoría tenga un tipo válido")
    public void categoriaTieneTipoValido() {

        // ARRANGE
        Categoria categoria = new Categoria(
                1,
                Categoria.NombreCategoria.sueldo,
                Categoria.TipoCategoria.ingreso
        );

        // ACT
        System.out.println(categoria);

        // ASSERT
        assertNotNull(categoria);

        assertTrue(
                categoria.getTipo() == Categoria.TipoCategoria.ingreso
                        || categoria.getTipo() == Categoria.TipoCategoria.gasto
        );
    }

    @Test
    @DisplayName("CAT-03 - Verificar que una categoría personalizada pueda crearse correctamente")
    public void crearCategoriaPersonalizada() {

        // ARRANGE

        // DataFaker
        Faker faker = new Faker();

        CategoriaPersonalizada categoriaPersonalizada =
                new CategoriaPersonalizada(
                        1,
                        faker.commerce().department(),
                        CategoriaPersonalizada.TipoCategoria.gasto
                );

        // ACT
        System.out.println(categoriaPersonalizada);

        // ASSERT
        assertNotNull(categoriaPersonalizada);

        assertNotNull(
                categoriaPersonalizada.getNombre()
        );

        assertFalse(
                categoriaPersonalizada.getNombre().isBlank()
        );

        assertEquals(
                CategoriaPersonalizada.TipoCategoria.gasto,
                categoriaPersonalizada.getTipo()
        );
    }

    @Test
    @DisplayName("CAT-04 - Verificar que una categoría personalizada tenga un nombre válido")
    public void categoriaPersonalizadaTieneNombreValido() {

        // ARRANGE

        // DataFaker
        Faker faker = new Faker();

        CategoriaPersonalizada categoriaPersonalizada =
                new CategoriaPersonalizada(
                        1,
                        faker.commerce().department(),
                        CategoriaPersonalizada.TipoCategoria.ingreso
                );

        // ACT
        System.out.println(categoriaPersonalizada);

        // ASSERT
        assertNotNull(categoriaPersonalizada);

        assertTrue(
                categoriaPersonalizada.getNombre().length() > 0
        );

        assertTrue(
                categoriaPersonalizada.getNombre().length() <= 100
        );
    }

    @Test
    @DisplayName("CAT-05 - Verificar que una categoría personalizada tenga un tipo válido")
    public void categoriaPersonalizadaTieneTipoValido() {

        // ARRANGE

        // DataFaker
        Faker faker = new Faker();

        CategoriaPersonalizada categoriaPersonalizada =
                new CategoriaPersonalizada(
                        1,
                        faker.commerce().department(),
                        CategoriaPersonalizada.TipoCategoria.gasto
                );

        // ACT
        System.out.println(categoriaPersonalizada);

        // ASSERT
        assertNotNull(categoriaPersonalizada);

        assertTrue(
                categoriaPersonalizada.getTipo() == CategoriaPersonalizada.TipoCategoria.ingreso
                        || categoriaPersonalizada.getTipo() == CategoriaPersonalizada.TipoCategoria.gasto
        );
    }
}