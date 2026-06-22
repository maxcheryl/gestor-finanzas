package cl.duoc.reportes_service.repository;

import cl.duoc.reportes_service.model.DetalleReporte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleReporteRepository
        extends JpaRepository<DetalleReporte, Integer> {

}