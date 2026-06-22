package cl.duoc.ingresos_service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngresoFijoDTO {

    private Integer id;

    @NotNull(message = "Usuario no puede estar vacio")
    @Positive(message = "Usuario debe ser mayor a cero")
    private Integer usuarioId;

    @NotNull(message = "Monto no puede estar vacio")
    @Positive(message = "El monto del ingreso debe ser mayor a cero")
    private Double monto;

    @NotNull(message = "Fecha no puede estar vacia")
    @PastOrPresent(message = "Fecha del ingreso no puede ser futura")
    private LocalDate fecha;

    @NotNull(message = "Entidad financiera no puede estar vacia")
    @Positive(message = "Entidad financiera debe ser mayor a cero")
    private Integer entidadFinancieraId;

    @Positive(message = "Categoria debe ser mayor a cero")
    private Integer categoriaId;
}
