package cl.duoc.presupuestos_service;

import cl.duoc.presupuestos_service.model.PresupuestoMen;
import net.datafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PresupuestosTest {


    @Test
    @DisplayName("PRE-01 - Verificar que un presupuesto mensual pueda crearse correctamente")
    public void crearPresupuestoMensual() {

        // ARRANGE

        // DataFaker
        Faker faker = new Faker();

        PresupuestoMen presupuesto = new PresupuestoMen(
                1,
                faker.number().numberBetween(1, 100),
                faker.number().numberBetween(1, 12),
                2026,
                faker.number().numberBetween(100000, 5000000),
                faker.number().numberBetween(100000, 5000000)
        );

        // ACT
        System.out.println(presupuesto);

        // ASSERT
        assertNotNull(presupuesto);

        assertTrue(
                presupuesto.getUsuarioId() > 0
        );

        assertTrue(
                presupuesto.getMes() >= 1 &&
                        presupuesto.getMes() <= 12
        );

        assertTrue(
                presupuesto.getAnio() > 0
        );
    }

    @Test
    @DisplayName("PRE-02 - Verificar que el mes del presupuesto sea válido")
    public void mesPresupuestoValido() {

        // ARRANGE
        PresupuestoMen presupuesto = new PresupuestoMen(
                1,
                5,
                6,
                2026,
                500000,
                450000
        );

        // ACT
        System.out.println(presupuesto);

        // ASSERT
        assertTrue(
                presupuesto.getMes() >= 1 &&
                        presupuesto.getMes() <= 12
        );
    }

    @Test
    @DisplayName("PRE-03 - Verificar que el monto inicial sea válido")
    public void montoInicialValido() {

        // ARRANGE
        PresupuestoMen presupuesto = new PresupuestoMen(
                1,
                8,
                7,
                2026,
                1000000,
                850000
        );

        // ACT
        System.out.println(presupuesto);

        // ASSERT
        assertTrue(
                presupuesto.getMontoInicial() > 0
        );
    }

    @Test
    @DisplayName("PRE-04 - Verificar que el monto disponible sea válido")
    public void montoDisponibleValido() {

        // ARRANGE
        PresupuestoMen presupuesto = new PresupuestoMen(
                1,
                10,
                5,
                2026,
                800000,
                600000
        );

        // ACT
        System.out.println(presupuesto);

        // ASSERT
        assertTrue(
                presupuesto.getMontoActualDisp() >= 0
        );
    }

    @Test
    @DisplayName("PRE-05 - Verificar que el monto disponible pueda disminuir por gastos")
    public void disminuirMontoDisponible() {

        // ARRANGE
        PresupuestoMen presupuesto = new PresupuestoMen(
                1,
                12,
                8,
                2026,
                1000000,
                1000000
        );

        // ACT
        presupuesto.setMontoActualDisp(
                presupuesto.getMontoActualDisp() - 250000
        );

        System.out.println(presupuesto);

        // ASSERT
        assertEquals(
                750000,
                presupuesto.getMontoActualDisp()
        );
    }


}
