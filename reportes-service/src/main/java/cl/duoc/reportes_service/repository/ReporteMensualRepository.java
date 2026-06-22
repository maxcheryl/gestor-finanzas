package cl.duoc.reportes_service.repository;

import cl.duoc.reportes_service.model.ReporteMensual;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReporteMensualRepository
        extends JpaRepository<ReporteMensual, Integer> {

}