package cl.duoc.presupuestos_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GastoDTO {
    private Integer id;
    private Integer usuarioId;
    private Double monto;
    private LocalDate fecha;
    private String descripcion;
    private Integer categoriaId;
    private Integer categoriaPersId;
}