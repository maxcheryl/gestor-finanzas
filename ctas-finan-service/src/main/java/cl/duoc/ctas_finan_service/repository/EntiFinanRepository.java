package cl.duoc.ctas_finan_service.repository;

import cl.duoc.ctas_finan_service.model.EntiFinan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntiFinanRepository
        extends JpaRepository<EntiFinan, Integer> {

}