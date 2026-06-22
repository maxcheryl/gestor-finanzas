package cl.duoc.reportes_service.model;

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
@Table(name = "detalle_reporte")
public class DetalleReporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "reporte_id", nullable = false)
    private ReporteMensual reporte;

    @Column(name = "categoria_id", nullable = false)
    private Integer categoriaId;

    @Column(name = "total_gastado", nullable = false)
    private Double totalGastado;
}