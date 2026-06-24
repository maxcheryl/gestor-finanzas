package cl.duoc.his_ahorro_service;

import cl.duoc.his_ahorro_service.model.AhorroAcumulado;
import net.datafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HisAhorroTest {


    @Test
    @DisplayName("HA-01 - Verificar que un ahorro acumulado pueda crearse correctamente")
    public void crearAhorroAcumulado() {

        // ARRANGE

        // DataFaker
        Faker faker = new Faker();

        AhorroAcumulado ahorro = new AhorroAcumulado(
                1,
                faker.number().numberBetween(1, 50),
                faker.number().numberBetween(1, 100),
                faker.number().randomDouble(2, 1000, 1000000)
        );

        // ACT
        System.out.println(ahorro);

        // ASSERT
        assertNotNull(ahorro);

        assertTrue(
                ahorro.getMetaId() > 0
        );

        assertTrue(
                ahorro.getUsuarioId() > 0
        );

        assertTrue(
                ahorro.getSaldoTotalAhorrado() >= 0
        );
    }

    @Test
    @DisplayName("HA-02 - Verificar que un ahorro acumulado tenga una meta asociada")
    public void ahorroTieneMetaAsociada() {

        // ARRANGE
        AhorroAcumulado ahorro = new AhorroAcumulado(
                1,
                10,
                5,
                50000.0
        );

        // ACT
        System.out.println(ahorro);

        // ASSERT
        assertNotNull(
                ahorro.getMetaId()
        );

        assertTrue(
                ahorro.getMetaId() > 0
        );
    }

    @Test
    @DisplayName("HA-03 - Verificar que un ahorro acumulado tenga un usuario asociado")
    public void ahorroTieneUsuarioAsociado() {

        // ARRANGE
        AhorroAcumulado ahorro = new AhorroAcumulado(
                1,
                3,
                15,
                75000.0
        );

        // ACT
        System.out.println(ahorro);

        // ASSERT
        assertNotNull(
                ahorro.getUsuarioId()
        );

        assertTrue(
                ahorro.getUsuarioId() > 0
        );
    }

    @Test
    @DisplayName("HA-04 - Verificar que el saldo total ahorrado sea válido")
    public void saldoAhorradoValido() {

        // ARRANGE
        AhorroAcumulado ahorro = new AhorroAcumulado(
                1,
                8,
                20,
                120000.0
        );

        // ACT
        System.out.println(ahorro);

        // ASSERT
        assertTrue(
                ahorro.getSaldoTotalAhorrado() >= 0
        );
    }

    @Test
    @DisplayName("HA-05 - Verificar que un ahorro acumulado pueda aumentar su saldo")
    public void aumentarSaldoAhorrado() {

        // ARRANGE
        AhorroAcumulado ahorro = new AhorroAcumulado(
                1,
                5,
                12,
                50000.0
        );

        // ACT
        ahorro.setSaldoTotalAhorrado(
                ahorro.getSaldoTotalAhorrado() + 25000.0
        );

        System.out.println(ahorro);

        // ASSERT
        assertEquals(
                75000.0,
                ahorro.getSaldoTotalAhorrado()
        );
    }


}
