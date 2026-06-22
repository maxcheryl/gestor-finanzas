package cl.duoc.ctas_finan_service.repository;

import cl.duoc.ctas_finan_service.model.MetodoPago;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetodoPagoRepository
        extends JpaRepository<MetodoPago, Integer> {

}