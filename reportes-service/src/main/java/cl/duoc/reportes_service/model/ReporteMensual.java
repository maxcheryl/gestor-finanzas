package cl.duoc.reportes_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "reporte_mensual")
public class ReporteMensual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "usuario_id", nullable = false)
    private Integer usuarioId;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(name = "ingresos_totales", nullable = false)
    private Double ingresosTotales;

    @Column(name = "gastos_totales", nullable = false)
    private Double gastosTotales;


    @Column(name = "saldo_neto", nullable = false)
    private Double saldoNeto;
}