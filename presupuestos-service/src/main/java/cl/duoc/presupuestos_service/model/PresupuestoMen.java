package cl.duoc.presupuestos_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "presupuesto_mensual")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PresupuestoMen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "usuario_id", nullable = false)
    private Integer usuarioId;

    @Column(name = "mes", nullable = false)
    private Integer mes;

    @Column(name = "anio", nullable = false)
    private Integer anio;

    @Column(name = "monto_inicial", nullable = false)
    private Integer montoInicial;

    @Column(name = "monto_actual_disponible", nullable = false)
    private Integer montoActualDisp;
}
