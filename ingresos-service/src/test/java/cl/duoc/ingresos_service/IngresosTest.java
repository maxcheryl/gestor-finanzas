package cl.duoc.ingresos_service;

import cl.duoc.ingresos_service.model.IngresoExtra;
import cl.duoc.ingresos_service.model.IngresoFijo;
import net.datafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class IngresosTest {


    @Test
    @DisplayName("ING-01 - Verificar que un ingreso extra pueda crearse correctamente")
    public void crearIngresoExtra() {

        // ARRANGE

        // DataFaker
        Faker faker = new Faker();

        IngresoExtra ingreso = new IngresoExtra(
                1,
                faker.number().numberBetween(1, 100),
                faker.number().randomDouble(2, 10000, 1000000),
                LocalDate.now(),
                faker.lorem().sentence(),
                faker.number().numberBetween(1, 20),
                faker.number().numberBetween(1, 20)
        );

        // ACT
        System.out.println(ingreso);

        // ASSERT
        assertNotNull(ingreso);

        assertTrue(
                ingreso.getUsuarioId() > 0
        );

        assertTrue(
                ingreso.getMonto() > 0
        );

        assertNotNull(
                ingreso.getFecha()
        );
    }

    @Test
    @DisplayName("ING-02 - Verificar que un ingreso fijo pueda crearse correctamente")
    public void crearIngresoFijo() {

        // ARRANGE

        // DataFaker
        Faker faker = new Faker();

        IngresoFijo ingreso = new IngresoFijo(
                1,
                faker.number().numberBetween(1, 100),
                faker.number().randomDouble(2, 500000, 3000000),
                LocalDate.now(),
                faker.number().numberBetween(1, 20),
                faker.number().numberBetween(1, 20)
        );

        // ACT
        System.out.println(ingreso);

        // ASSERT
        assertNotNull(ingreso);

        assertTrue(
                ingreso.getUsuarioId() > 0
        );

        assertTrue(
                ingreso.getMonto() > 0
        );

        assertNotNull(
                ingreso.getFecha()
        );
    }

    @Test
    @DisplayName("ING-03 - Verificar que un ingreso extra tenga una descripción válida")
    public void ingresoExtraTieneDescripcionValida() {

        // ARRANGE

        // DataFaker
        Faker faker = new Faker();

        IngresoExtra ingreso = new IngresoExtra(
                1,
                10,
                50000.0,
                LocalDate.now(),
                faker.lorem().sentence(),
                1,
                1
        );

        // ACT
        System.out.println(ingreso);

        // ASSERT
        assertNotNull(
                ingreso.getDescripcion()
        );

        assertFalse(
                ingreso.getDescripcion().isBlank()
        );
    }

    @Test
    @DisplayName("ING-04 - Verificar que un ingreso tenga una entidad financiera asociada")
    public void ingresoTieneEntidadFinanciera() {

        // ARRANGE
        IngresoFijo ingreso = new IngresoFijo(
                1,
                5,
                850000.0,
                LocalDate.now(),
                2,
                1
        );

        // ACT
        System.out.println(ingreso);

        // ASSERT
        assertNotNull(
                ingreso.getEntidadFinancieraId()
        );

        assertTrue(
                ingreso.getEntidadFinancieraId() > 0
        );
    }

    @Test
    @DisplayName("ING-05 - Verificar que el monto de un ingreso sea válido")
    public void montoIngresoValido() {

        // ARRANGE
        IngresoExtra ingreso = new IngresoExtra(
                1,
                8,
                125000.0,
                LocalDate.now(),
                "Venta de articulos",
                1,
                2
        );

        // ACT
        System.out.println(ingreso);

        // ASSERT
        assertTrue(
                ingreso.getMonto() > 0
        );
    }


}
