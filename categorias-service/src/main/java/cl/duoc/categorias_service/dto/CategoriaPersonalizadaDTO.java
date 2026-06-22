package cl.duoc.categorias_service.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaPersonalizadaDTO {

    private Integer id;

    @NotBlank(message = "Nombre no puede estar vacio")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    private String nombre;

    @NotNull(message = "Tipo no puede estar vacio")
    private TipoCategoria tipo;

    public enum TipoCategoria {
        ingreso, gasto
    }
}
