package cl.duoc.reportes_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReporteMensualResponseDTO {

    private Integer id;
    private UsuarioDTO usuario;
    private LocalDate fecha;
    private Double ingresosTotales;
    private Double gastosTotales;
    private Double saldoNeto;
}