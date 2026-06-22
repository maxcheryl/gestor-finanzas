package cl.duoc.reportes_service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReporteMensualDTO {

    private Integer id;

    @NotNull(message = "Usuario no puede estar vacio")
    private Integer usuarioId;

    @NotNull(message = "Fecha no puede estar vacia")
    private LocalDate fecha;

    @NotNull(message = "Ingresos totales no pueden estar vacios")
    @PositiveOrZero(message = "Los ingresos no pueden ser negativos")
    private Double ingresosTotales;

    @NotNull(message = "Gastos totales no pueden estar vacios")
    @PositiveOrZero(message = "Los gastos no pueden ser negativos")
    private Double gastosTotales;

    @NotNull(message = "Saldo neto no puede estar vacio")
    private Double saldoNeto;
}