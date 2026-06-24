package cl.duoc.reportes_service;

import cl.duoc.reportes_service.model.DetalleReporte;
import cl.duoc.reportes_service.model.ReporteMensual;
import net.datafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ReportesTest {

    @Test
    @DisplayName("REP-01 - Verificar que un reporte mensual pueda crearse correctamente")
    public void crearReporteMensual() {

        // ARRANGE

        // DataFaker
        Faker faker = new Faker();

        ReporteMensual reporte = new ReporteMensual(
                1,
                faker.number().numberBetween(1, 100),
                LocalDate.now(),
                faker.number().randomDouble(2, 500000, 5000000),
                faker.number().randomDouble(2, 100000, 3000000),
                faker.number().randomDouble(2, 100000, 2000000),
                new ArrayList<>()
        );

        // ACT
        System.out.println(reporte);

        // ASSERT
        assertNotNull(reporte);

        assertTrue(
                reporte.getUsuarioId() > 0
        );

        assertNotNull(
                reporte.getFecha()
        );

        assertTrue(
                reporte.getIngresosTotales() > 0
        );

        assertTrue(
                reporte.getGastosTotales() > 0
        );
    }

    @Test
    @DisplayName("REP-02 - Verificar que un detalle de reporte pueda crearse correctamente")
    public void crearDetalleReporte() {

        // ARRANGE

        ReporteMensual reporte = new ReporteMensual();
        reporte.setId(1);

        DetalleReporte detalle = new DetalleReporte(
                1,
                reporte,
                2,
                50000.0
        );

        // ACT
        System.out.println("Detalle ID: " + detalle.getId());

        // ASSERT
        assertNotNull(detalle);

        assertNotNull(
                detalle.getReporte()
        );

        assertTrue(
                detalle.getCategoriaId() > 0
        );

        assertTrue(
                detalle.getTotalGastado() >= 0
        );
    }

    @Test
    @DisplayName("REP-03 - Verificar que un detalle pueda asociarse a un reporte")
    public void asociarDetalleAReporte() {

        // ARRANGE

        ReporteMensual reporte = new ReporteMensual();
        reporte.setId(1);

        DetalleReporte detalle = new DetalleReporte(
                1,
                reporte,
                3,
                75000.0
        );

        // ACT

        reporte.getDetalles().add(detalle);

        System.out.println(
                "Cantidad detalles: " +
                        reporte.getDetalles().size()
        );

        // ASSERT

        assertEquals(
                1,
                reporte.getDetalles().size()
        );

        assertTrue(
                reporte.getDetalles().contains(detalle)
        );
    }

    @Test
    @DisplayName("REP-04 - Verificar que un detalle pueda eliminarse de un reporte")
    public void eliminarDetalleDeReporte() {

        // ARRANGE

        ReporteMensual reporte = new ReporteMensual();
        reporte.setId(1);

        DetalleReporte detalle = new DetalleReporte(
                1,
                reporte,
                5,
                100000.0
        );

        // ACT

        reporte.getDetalles().add(detalle);
        reporte.getDetalles().remove(detalle);

        System.out.println(
                "Cantidad detalles: " +
                        reporte.getDetalles().size()
        );

        // ASSERT

        assertFalse(
                reporte.getDetalles().contains(detalle)
        );

        assertTrue(
                reporte.getDetalles().isEmpty()
        );
    }

    @Test
    @DisplayName("REP-05 - Verificar que el saldo neto del reporte sea válido")
    public void saldoNetoValido() {

        // ARRANGE

        ReporteMensual reporte = new ReporteMensual(
                1,
                10,
                LocalDate.now(),
                1000000.0,
                400000.0,
                600000.0,
                new ArrayList<>()
        );

        // ACT

        System.out.println(
                "Saldo neto: " +
                        reporte.getSaldoNeto()
        );

        // ASSERT

        assertEquals(
                reporte.getIngresosTotales() -
                        reporte.getGastosTotales(),
                reporte.getSaldoNeto()
        );
    }
}