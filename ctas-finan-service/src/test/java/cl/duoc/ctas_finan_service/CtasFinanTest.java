package cl.duoc.ctas_finan_service;

import cl.duoc.ctas_finan_service.model.EntiFinan;
import cl.duoc.ctas_finan_service.model.MetodoPago;
import net.datafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CtasFinanTest {

    @Test
    @DisplayName("CTA-01 - Verificar que una entidad financiera pueda crearse correctamente")
    public void crearEntidadFinanciera() {

        // ARRANGE

        // DataFaker
        Faker faker = new Faker();

        EntiFinan entidad = new EntiFinan(
                1,
                faker.number().numberBetween(1, 100),
                faker.company().name(),
                faker.number().numberBetween(1, 20)
        );

        // ACT
        System.out.println(entidad);

        // ASSERT
        assertNotNull(entidad);

        assertTrue(
                entidad.getUsuarioId() > 0
        );

        assertNotNull(
                entidad.getNombreEntidad()
        );

        assertFalse(
                entidad.getNombreEntidad().isBlank()
        );

        assertTrue(
                entidad.getMetodoPagoId() > 0
        );
    }

    @Test
    @DisplayName("CTA-02 - Verificar que el nombre de la entidad financiera sea válido")
    public void nombreEntidadValido() {

        // ARRANGE

        // DataFaker
        Faker faker = new Faker();

        EntiFinan entidad = new EntiFinan(
                1,
                10,
                faker.company().name(),
                2
        );

        // ACT
        System.out.println(entidad);

        // ASSERT
        assertTrue(
                entidad.getNombreEntidad().length() > 0
        );

        assertTrue(
                entidad.getNombreEntidad().length() <= 100
        );
    }

    @Test
    @DisplayName("CTA-03 - Verificar que un método de pago pueda crearse correctamente")
    public void crearMetodoPago() {

        // ARRANGE

        // DataFaker
        Faker faker = new Faker();

        MetodoPago metodoPago = new MetodoPago(
                1,
                faker.business().creditCardType()
        );

        // ACT
        System.out.println(metodoPago);

        // ASSERT
        assertNotNull(metodoPago);

        assertNotNull(
                metodoPago.getNombreMetodo()
        );

        assertFalse(
                metodoPago.getNombreMetodo().isBlank()
        );
    }

    @Test
    @DisplayName("CTA-04 - Verificar que el nombre del método de pago sea válido")
    public void nombreMetodoValido() {

        // ARRANGE

        // DataFaker
        Faker faker = new Faker();

        MetodoPago metodoPago = new MetodoPago(
                1,
                faker.business().creditCardType()
        );

        // ACT
        System.out.println(metodoPago);

        // ASSERT
        assertTrue(
                metodoPago.getNombreMetodo().length() > 0
        );

        assertTrue(
                metodoPago.getNombreMetodo().length() <= 100
        );
    }

    @Test
    @DisplayName("CTA-05 - Verificar que una entidad financiera tenga un método de pago asociado")
    public void entidadTieneMetodoPagoAsociado() {

        // ARRANGE

        EntiFinan entidad = new EntiFinan(
                1,
                5,
                "Banco Estado",
                1
        );

        MetodoPago metodoPago = new MetodoPago(
                1,
                "Tarjeta Debito"
        );

        // ACT
        System.out.println(entidad);
        System.out.println(metodoPago);

        // ASSERT
        assertEquals(
                1,
                entidad.getMetodoPagoId()
        );

        assertEquals(
                1,
                metodoPago.getId()
        );
    }


}
