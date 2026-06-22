package cl.duoc.usuarios_service.repository;

import cl.duoc.usuarios_service.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository
        extends JpaRepository<Usuario, Integer> {
}