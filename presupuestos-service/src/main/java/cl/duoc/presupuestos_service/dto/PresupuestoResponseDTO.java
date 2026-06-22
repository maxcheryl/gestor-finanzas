package cl.duoc.presupuestos_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PresupuestoResponseDTO {
    private Integer id;
    private UsuarioDTO usuario;
    private Integer mes;
    private Integer anio;
    private Integer montoInicial;
    private Integer montoActualDisp;
}
