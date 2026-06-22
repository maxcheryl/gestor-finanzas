package cl.duoc.ingresos_service.repository;

import cl.duoc.ingresos_service.model.IngresoFijo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngresoFijoRepository extends JpaRepository<IngresoFijo, Integer> {
}
