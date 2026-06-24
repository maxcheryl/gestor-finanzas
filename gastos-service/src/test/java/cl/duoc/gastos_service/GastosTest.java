package cl.duoc.gastos_service;

import cl.duoc.gastos_service.model.Gasto;
import cl.duoc.gastos_service.model.GastoRecurrente;
import net.datafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class GastosTest {


    @Test
    @DisplayName("GAS-01 - Verificar que un gasto pueda crearse correctamente")
    public void crearGasto() {

        // ARRANGE

        // DataFaker
        Faker faker = new Faker();

        Gasto gasto = new Gasto(
                1,
                faker.number().numberBetween(1, 100),
                faker.number().randomDouble(2, 1000, 500000),
                LocalDate.now(),
                faker.lorem().sentence(),
                faker.number().numberBetween(1, 20),
                faker.number().numberBetween(1, 20)
        );

        // ACT
        System.out.println(gasto);

        // ASSERT
        assertNotNull(gasto);

        assertTrue(
                gasto.getUsuarioId() > 0
        );

        assertTrue(
                gasto.getMonto() > 0
        );

        assertNotNull(
                gasto.getFecha()
        );
    }

    @Test
    @DisplayName("GAS-02 - Verificar que un gasto tenga una fecha válida")
    public void gastoTieneFechaValida() {

        // ARRANGE
        Gasto gasto = new Gasto(
                1,
                5,
                25000.0,
                LocalDate.now(),
                "Compra supermercado",
                1,
                null
        );

        // ACT
        System.out.println(gasto);

        // ASSERT
        assertEquals(
                LocalDate.now(),
                gasto.getFecha()
        );
    }

    @Test
    @DisplayName("GAS-03 - Verificar que un gasto recurrente pueda crearse correctamente")
    public void crearGastoRecurrente() {

        // ARRANGE

        // DataFaker
        Faker faker = new Faker();

        GastoRecurrente gastoRecurrente =
                new GastoRecurrente(
                        1,
                        faker.number().numberBetween(1, 100),
                        faker.number().randomDouble(2, 5000, 100000),
                        faker.lorem().sentence(),
                        faker.number().numberBetween(1, 20),
                        faker.number().numberBetween(1, 20)
                );

        // ACT
        System.out.println(gastoRecurrente);

        // ASSERT
        assertNotNull(gastoRecurrente);

        assertTrue(
                gastoRecurrente.getUsuarioId() > 0
        );

        assertTrue(
                gastoRecurrente.getMonto() > 0
        );
    }

    @Test
    @DisplayName("GAS-04 - Verificar que un gasto tenga una descripción válida")
    public void descripcionGastoValida() {

        // ARRANGE

        // DataFaker
        Faker faker = new Faker();

        Gasto gasto = new Gasto(
                1,
                10,
                15000.0,
                LocalDate.now(),
                faker.lorem().sentence(),
                2,
                null
        );

        // ACT
        System.out.println(gasto);

        // ASSERT
        assertNotNull(
                gasto.getDescripcion()
        );

        assertFalse(
                gasto.getDescripcion().isBlank()
        );
    }

    @Test
    @DisplayName("GAS-05 - Verificar que un gasto pueda asociarse a una categoría")
    public void gastoTieneCategoriaAsociada() {

        // ARRANGE
        Gasto gasto = new Gasto(
                1,
                8,
                35000.0,
                LocalDate.now(),
                "Pago de transporte",
                3,
                null
        );

        // ACT
        System.out.println(gasto);

        // ASSERT
        assertNotNull(
                gasto.getCategoriaId()
        );

        assertTrue(
                gasto.getCategoriaId() > 0
        );
    }


}
