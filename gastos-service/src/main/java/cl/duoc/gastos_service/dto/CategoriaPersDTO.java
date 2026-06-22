package cl.duoc.gastos_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaPersDTO {
    private Integer id;

    private String nombre;

    private String tipo;
}