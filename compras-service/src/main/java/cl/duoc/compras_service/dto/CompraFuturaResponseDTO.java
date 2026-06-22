package cl.duoc.compras_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompraFuturaResponseDTO {
    private Integer id;
    private UsuarioDTO usuario;
    private String nombreProducto;
    private Integer precioEstimado;
    private String nota;
}