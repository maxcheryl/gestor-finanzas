package cl.duoc.compras_service;

import cl.duoc.compras_service.model.CompraFutura;
import net.datafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ComprasTest {


    @Test
    @DisplayName("COM-01 - Verificar que una compra futura pueda crearse correctamente")
    public void crearCompraFutura() {

        // ARRANGE

        // DataFaker
        Faker faker = new Faker();

        CompraFutura compra = new CompraFutura(
                1,
                faker.number().numberBetween(1, 100),
                faker.commerce().productName(),
                faker.number().numberBetween(10000, 500000),
                faker.lorem().sentence()
        );

        // ACT
        System.out.println(compra);

        // ASSERT
        assertNotNull(compra);

        assertNotNull(compra.getNombreProducto());

        assertFalse(
                compra.getNombreProducto().isBlank()
        );

        assertTrue(
                compra.getPrecioEstimado() > 0
        );
    }

    @Test
    @DisplayName("COM-02 - Verificar que una compra futura tenga un usuario asociado")
    public void compraTieneUsuarioAsociado() {

        // ARRANGE

        // DataFaker
        Faker faker = new Faker();

        CompraFutura compra = new CompraFutura(
                1,
                faker.number().numberBetween(1, 100),
                "Notebook Gamer",
                850000,
                "Compra para trabajo"
        );

        // ACT
        System.out.println(compra);

        // ASSERT
        assertNotNull(compra);

        assertTrue(
                compra.getUsuarioId() > 0
        );
    }

    @Test
    @DisplayName("COM-03 - Verificar que el precio estimado sea válido")
    public void precioEstimadoValido() {

        // ARRANGE
        CompraFutura compra = new CompraFutura(
                1,
                10,
                "Televisor",
                450000,
                "Oferta de temporada"
        );

        // ACT
        System.out.println(compra);

        // ASSERT
        assertTrue(
                compra.getPrecioEstimado() > 0
        );
    }

    @Test
    @DisplayName("COM-04 - Verificar que una compra futura pueda contener una nota")
    public void compraContieneNota() {

        // ARRANGE

        // DataFaker
        Faker faker = new Faker();

        CompraFutura compra = new CompraFutura(
                1,
                5,
                "Celular",
                300000,
                faker.lorem().sentence()
        );

        // ACT
        System.out.println(compra);

        // ASSERT
        assertNotNull(
                compra.getNota()
        );

        assertFalse(
                compra.getNota().isBlank()
        );
    }

    @Test
    @DisplayName("COM-05 - Verificar que el nombre del producto sea válido")
    public void nombreProductoValido() {

        // ARRANGE

        // DataFaker
        Faker faker = new Faker();

        CompraFutura compra = new CompraFutura(
                1,
                8,
                faker.commerce().productName(),
                150000,
                "Compra planificada"
        );

        // ACT
        System.out.println(compra);

        // ASSERT
        assertTrue(
                compra.getNombreProducto().length() > 0
        );

        assertTrue(
                compra.getNombreProducto().length() <= 100
        );
    }

}
