package cl.duoc.ingresos_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngresoFijoResponseDTO {
    private Integer id;

    private UsuarioDTO usuarioId;

    private Double monto;

    private LocalDate fecha;

    private EntidadFinancieraDTO entidadFinancieraId;

    private CategoriaDTO categoriaId;
}
