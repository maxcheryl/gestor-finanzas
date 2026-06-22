package cl.duoc.metas_service.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetaMensualResponseDTO {
    private Integer id;
    private String nombre;
    private Double cuotaMensual;
    private UsuarioDTO usuario;
}
