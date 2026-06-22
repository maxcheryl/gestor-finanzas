package cl.duoc.ctas_finan_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntiFinanResponseDTO {

    private Integer id;
    private UsuarioDTO usuario;
    private String nombreEntidad;
    private MetodoPagoDTO metodoPago;
}
