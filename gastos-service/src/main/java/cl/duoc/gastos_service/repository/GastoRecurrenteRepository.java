package cl.duoc.gastos_service.repository;

import cl.duoc.gastos_service.model.GastoRecurrente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GastoRecurrenteRepository  extends JpaRepository<GastoRecurrente, Integer> {
}
