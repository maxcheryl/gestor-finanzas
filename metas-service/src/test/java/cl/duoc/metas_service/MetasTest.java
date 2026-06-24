package cl.duoc.metas_service;

import cl.duoc.metas_service.model.MetaMensual;
import net.datafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MetasTest {


    @Test
    @DisplayName("MET-01 - Verificar que una meta mensual pueda crearse correctamente")
    public void crearMetaMensual() {

        // ARRANGE

        // DataFaker
        Faker faker = new Faker();

        MetaMensual meta = new MetaMensual(
                1,
                faker.number().numberBetween(1, 100),
                faker.company().profession(),
                faker.number().randomDouble(2, 10000, 500000)
        );

        // ACT
        System.out.println(meta);

        // ASSERT
        assertNotNull(meta);

        assertTrue(
                meta.getUsuarioId() > 0
        );

        assertNotNull(
                meta.getNombre()
        );

        assertFalse(
                meta.getNombre().isBlank()
        );

        assertTrue(
                meta.getCuotaMensual() > 0
        );
    }

    @Test
    @DisplayName("MET-02 - Verificar que una meta mensual tenga un usuario asociado")
    public void metaTieneUsuarioAsociado() {

        // ARRANGE
        MetaMensual meta = new MetaMensual(
                1,
                10,
                "Vacaciones",
                50000.0
        );

        // ACT
        System.out.println(meta);

        // ASSERT
        assertNotNull(
                meta.getUsuarioId()
        );

        assertTrue(
                meta.getUsuarioId() > 0
        );
    }

    @Test
    @DisplayName("MET-03 - Verificar que el nombre de la meta sea válido")
    public void nombreMetaValido() {

        // ARRANGE

        // DataFaker
        Faker faker = new Faker();

        MetaMensual meta = new MetaMensual(
                1,
                5,
                faker.company().profession(),
                25000.0
        );

        // ACT
        System.out.println(meta);

        // ASSERT
        assertTrue(
                meta.getNombre().length() > 0
        );

        assertTrue(
                meta.getNombre().length() <= 100
        );
    }

    @Test
    @DisplayName("MET-04 - Verificar que la cuota mensual sea válida")
    public void cuotaMensualValida() {

        // ARRANGE
        MetaMensual meta = new MetaMensual(
                1,
                15,
                "Fondo Emergencia",
                75000.0
        );

        // ACT
        System.out.println(meta);

        // ASSERT
        assertTrue(
                meta.getCuotaMensual() > 0
        );
    }

    @Test
    @DisplayName("MET-05 - Verificar que una meta mensual pueda aumentar su cuota")
    public void aumentarCuotaMensual() {

        // ARRANGE
        MetaMensual meta = new MetaMensual(
                1,
                20,
                "Vehiculo",
                100000.0
        );

        // ACT
        meta.setCuotaMensual(
                meta.getCuotaMensual() + 50000.0
        );

        System.out.println(meta);

        // ASSERT
        assertEquals(
                150000.0,
                meta.getCuotaMensual()
        );
    }


}
