package cl.duoc.gastos_service.repository;

import cl.duoc.gastos_service.model.Gasto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GastoRepository extends JpaRepository<Gasto, Integer> {
}
