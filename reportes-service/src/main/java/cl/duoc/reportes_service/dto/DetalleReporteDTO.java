package cl.duoc.reportes_service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleReporteDTO {

    private Integer id;

    @NotNull(message = "Reporte no puede estar vacio")
    @Positive(message = "Reporte debe ser mayor a cero")
    private Integer reporteId;

    @NotNull(message = "Categoria no puede estar vacia")
    @Positive(message = "Categoria debe ser mayor a cero")
    private Integer categoriaId;

    @NotNull(message = "Total gastado no puede estar vacio")
    @PositiveOrZero(
            message = "El total gastado no puede ser negativo"
    )
    private Double totalGastado;
}
