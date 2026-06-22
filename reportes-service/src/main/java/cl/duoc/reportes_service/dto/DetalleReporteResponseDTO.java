package cl.duoc.reportes_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleReporteResponseDTO {

    private Integer id;
    private ReporteMensualResponseDTO reporte;
    private CategoriaDTO categoria;
    private Double totalGastado;
}
