package cl.duoc.ingresos_service.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntidadFinancieraDTO {
    private Integer id;
    private Integer usuarioId;
    private String nombreEntidad;
    private Integer metodoPagoId;
}
