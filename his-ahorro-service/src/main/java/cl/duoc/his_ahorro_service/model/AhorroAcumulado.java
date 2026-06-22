package cl.duoc.his_ahorro_service.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "ahorro_acumulado")
public class AhorroAcumulado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "meta_id", nullable = false)
    private Integer metaId;


    @Column(name = "usuario_id", nullable = false)
    private Integer usuarioId;


    @Column(name = "saldo_total_ahorrado", nullable = false)
    private Double saldoTotalAhorrado;
}