package cl.duoc.presupuestos_service.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PresupuestoMenDTO {

    @NotNull(message = "Usuario no puede estar vacio")
    @Positive(message = "Usuario debe ser mayor a cero")
    private Integer usuarioId;

    @NotNull(message = "Mes no puede estar vacio")
    @Min(value = 1, message = "Mes debe estar entre 1 y 12")
    @Max(value = 12, message = "Mes debe estar entre 1 y 12")
    private Integer mes;

    @NotNull(message = "Anio no puede estar vacio")
    @Positive(message = "Anio debe ser mayor a cero")
    private Integer anio;

    @NotNull(message = "Monto inicial no puede estar vacio")
    @Positive(message = "Monto inicial debe ser mayor a cero")
    private Integer montoInicial;

    // monto_actual_disponible no va, se calcula automáticamente
}
