package cl.duoc.his_ahorro_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetaMensualDTO {

    private Integer id;
    private Integer usuarioId;
    private String nombre;
    private Double cuotaMensual;
}