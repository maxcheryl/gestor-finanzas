package cl.duoc.categorias_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatPersResponseDTO {
    private Integer id;
    private String nombre;
    private TipoCategoria tipo;
    public enum TipoCategoria {
        ingreso, gasto
    }
}
