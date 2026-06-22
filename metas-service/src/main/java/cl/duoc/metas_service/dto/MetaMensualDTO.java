package cl.duoc.metas_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetaMensualDTO {
    private Integer id;

    @NotNull(message = "Usuario no puede estar vacio")
    @Positive(message = "Usuario debe ser mayor a cero")
    private Integer usuarioId;

    @NotBlank(message = "Nombre de la meta no puede estar vacio")
    @Size(max = 100, message = "El nombre de la meta no puede superar los 100 caracteres")
    private String nombre;

    @NotNull(message = "Cuota mensual no puede estar vacia")
    @Positive(message = "Cuota mensual debe ser mayor a cero")
    private Double cuotaMensual;
}
