package cl.duoc.presupuestos_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GastoRecurrenteDTO {
    private Integer id;
    private Integer usuarioId;
    private Double monto;
    private String descripcion;
    private Integer categoriaId;
    private Integer categoriaPersId;
}