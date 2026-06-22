package cl.duoc.compras_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "compra_futura")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompraFutura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "usuario_id", nullable = false)
    private Integer usuarioId;

    @Column(name = "nombre_producto", nullable = false, length = 100)
    private String nombreProducto;

    @Column(name = "precio_estimado", nullable = false)
    private Integer precioEstimado;

    @Column(length = 255)
    private String nota;
}
