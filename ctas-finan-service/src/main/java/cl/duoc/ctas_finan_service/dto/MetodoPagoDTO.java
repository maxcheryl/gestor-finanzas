package cl.duoc.ctas_finan_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetodoPagoDTO {

    private Integer id;

    @NotBlank(message = "Nombre del metodo no puede estar vacio")
    @Size(max = 100,
            message = "Nombre del metodo no puede superar los 100 caracteres")
    private String nombreMetodo;
}