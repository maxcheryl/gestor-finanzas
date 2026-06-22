package cl.duoc.compras_service.dto;

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
public class CompraFuturaDTO {

    @NotNull(message = "Usuario no puede estar vacio")
    @Positive(message = "Usuario debe ser mayor a cero")
    private Integer usuarioId;

    @NotBlank(message = "Nombre del producto no puede estar vacio")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    private String nombreProducto;

    @NotNull(message = "Precio estimado no puede estar vacio")
    @Positive(message = "Precio estimado debe ser mayor a cero")
    private Integer precioEstimado;

    @Size(max = 255)
    private String nota;
}

