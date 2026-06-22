package cl.duoc.gastos_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "gasto_recurrente")
public class GastoRecurrente
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "usuario_id", nullable = false)
    private Integer usuarioId;

    @Column(name = "monto", nullable = false)
    private Double monto;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @Column(name = "categoria_id")
    private Integer categoriaId;

    @Column(name = "categoria_pers_id")
    private Integer categoriaPersId;
}
