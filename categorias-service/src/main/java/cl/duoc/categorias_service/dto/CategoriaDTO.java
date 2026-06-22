package cl.duoc.categorias_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDTO {

    private Integer id;

    @NotNull(message = "Nombre no puede estar vacio")
    private NombreCategoria nombre;

    @NotNull(message = "Tipo no puede estar vacio")
    private TipoCategoria tipo;

    public enum NombreCategoria {
        sueldo, comida, transporte
    }

    public enum TipoCategoria {
        ingreso, gasto
    }
}
