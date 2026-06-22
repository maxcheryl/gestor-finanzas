package cl.duoc.his_ahorro_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AhorroAcumuladoResponseDTO {

    private Integer id;
    private UsuarioDTO usuario;
    private MetaMensualDTO meta;
    private Double saldoTotalAhorrado;
}