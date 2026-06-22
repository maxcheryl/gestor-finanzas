package cl.duoc.gastos_service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GastoRecurrenteDTO {
    private Integer id;

    @NotNull(message = "Usuario no puede estar vacio")
    @Positive(message = "Usuario debe ser mayor a cero")
    private Integer usuarioId;

    @NotNull(message = "Monto del gasto no puede estar vacio")
    @Positive(message = "Monto del gasto debe ser mayor a cero")
    private Double monto;

    @Size(max = 255, message = "Descripcion no puede superar los 255 caracteres")
    private String descripcion;

    @Positive(message = "Categoria debe ser mayor a cero")
    private Integer categoriaId;

    @Positive(message = "Categoria personalizada debe ser mayor a cero")
    private Integer categoriaPersId;
}
