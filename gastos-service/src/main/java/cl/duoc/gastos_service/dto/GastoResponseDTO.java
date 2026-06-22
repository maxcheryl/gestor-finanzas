package cl.duoc.gastos_service.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GastoResponseDTO {
    private Integer id;

    private UsuarioDTO usuario;

    private Double monto;

    private LocalDate fecha;

    private String descripcion;

    private CategoriaDTO categoria;

    private CategoriaPersDTO categoriaPers;
}
