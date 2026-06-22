package cl.duoc.his_ahorro_service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AhorroAcumuladoDTO {

    private Integer id;

    @NotNull(message = "Meta no puede estar vacia")
    private Integer metaId;

    @NotNull(message = "Usuario no puede estar vacio")
    private Integer usuarioId;

    @NotNull(message = "Saldo no puede estar vacio")
    @PositiveOrZero(message = "El saldo no puede ser negativo")//@PositiveOrZero para permitir 0 ahorrado y evitar negativos.
    private Double saldoTotalAhorrado;
}