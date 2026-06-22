package cl.duoc.categorias_service.repository;

import cl.duoc.categorias_service.model.CategoriaPersonalizada;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaPersonalizadaRepository extends JpaRepository<CategoriaPersonalizada, Integer> {
}
