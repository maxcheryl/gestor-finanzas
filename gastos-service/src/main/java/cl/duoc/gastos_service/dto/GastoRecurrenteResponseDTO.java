package cl.duoc.gastos_service.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GastoRecurrenteResponseDTO {
    private Integer id;

    private UsuarioDTO usuario;

    private Double monto;

    private String descripcion;

    private CategoriaDTO categoria;


    private CategoriaPersDTO categoriaPers;
}
