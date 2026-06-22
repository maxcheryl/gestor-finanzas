package cl.duoc.his_ahorro_service.repository;

import cl.duoc.his_ahorro_service.model.AhorroAcumulado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AhorroAcumuladoRepository
        extends JpaRepository<AhorroAcumulado, Integer> {

    Optional<AhorroAcumulado> findByMetaId(
            Integer metaId
    );

    List<AhorroAcumulado> findByUsuarioId(
            Integer usuarioId
    );
}