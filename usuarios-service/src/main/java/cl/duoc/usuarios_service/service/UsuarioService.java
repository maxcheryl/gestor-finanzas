package cl.duoc.usuarios_service.service;

import cl.duoc.usuarios_service.dto.UsuarioDTO;
import cl.duoc.usuarios_service.model.Usuario;
import cl.duoc.usuarios_service.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    // LISTAR
    public List<UsuarioDTO> getUsuarios() {
        return repository.findAll()
                .stream()
                .map(usuario ->
                        new UsuarioDTO(
                                usuario.getId(),
                                usuario.getNombre(),
                                usuario.getApellidoPaterno(),
                                usuario.getApellidoMaterno()
                        )
                )
                .toList();
    }

    // BUSCAR POR ID
    public Optional<UsuarioDTO> getUsuario(Integer id) {
        return repository.findById(id)
                .map(usuario -> new UsuarioDTO(
                                usuario.getId(),
                                usuario.getNombre(),
                                usuario.getApellidoPaterno(),
                                usuario.getApellidoMaterno()
                        )
                );
    }

    // GUARDAR
    public UsuarioDTO saveUsuario(UsuarioDTO dto) {

        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());

        Usuario guardado = repository.save(usuario);

        return new UsuarioDTO(
                guardado.getId(),
                guardado.getNombre(),
                guardado.getApellidoPaterno(),
                guardado.getApellidoMaterno()
        );
    }

    // ACTUALIZAR
    public UsuarioDTO updateUsuario(Integer id, UsuarioDTO dto) {

        Usuario existe = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado"));

        existe.setNombre(dto.getNombre());

        Usuario actualizado = repository.save(existe);

        return new UsuarioDTO(
                actualizado.getId(),
                actualizado.getNombre(),
                actualizado.getApellidoPaterno(),
                actualizado.getApellidoMaterno()
        );
    }

    // ELIMINAR
    public void deleteUsuario(Integer id) {

        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Usuario no encontrado");
        }
    }
}

