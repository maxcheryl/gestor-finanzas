package cl.duoc.metas_service.service;

import cl.duoc.metas_service.client.UsuarioClient;
import cl.duoc.metas_service.dto.MetaMensualDTO;
import cl.duoc.metas_service.dto.MetaMensualResponseDTO;
import cl.duoc.metas_service.dto.UsuarioDTO;
import cl.duoc.metas_service.model.MetaMensual;
import cl.duoc.metas_service.repository.MetaMensualRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MetaMensualService {

    @Autowired
    private MetaMensualRepository repository;

    @Autowired
    private UsuarioClient usuarioClient;

    public List<MetaMensualResponseDTO> getMetas() {
        return repository.findAll()
                .stream()
                .map(meta -> {
                    UsuarioDTO usuario = null;
                    try {
                        usuario = usuarioClient.obtenerUsuario(meta.getUsuarioId());
                    } catch (Exception e) {
                        usuario = new UsuarioDTO(meta.getUsuarioId(), "Usuario no disponible");
                    }

                    return new MetaMensualResponseDTO(
                            meta.getId(),
                            meta.getNombre(),
                            meta.getCuotaMensual(),
                            usuario
                    );
                }).toList();
    }

    public Optional<MetaMensualResponseDTO> getMeta(Integer id) {
        return repository.findById(id)
                .map(meta -> {
                    UsuarioDTO usuario = null;
                    try {
                        usuario = usuarioClient.obtenerUsuario(meta.getUsuarioId());
                    } catch (Exception e) {
                        usuario = new UsuarioDTO(meta.getUsuarioId(), "Usuario no disponible");
                    }

                    return new MetaMensualResponseDTO(
                            meta.getId(),
                            meta.getNombre(),
                            meta.getCuotaMensual(),
                            usuario
                    );
                });
    }

    public MetaMensualDTO saveMeta(MetaMensualDTO dto) {
        try {
            UsuarioDTO usuarioExterna = usuarioClient.obtenerUsuario(dto.getUsuarioId());
            if (usuarioExterna == null) {
                throw new RuntimeException("El usuario no existe.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Usuario no disponible");
        }

        MetaMensual meta = new MetaMensual();
        meta.setUsuarioId(dto.getUsuarioId());
        meta.setNombre(dto.getNombre());
        meta.setCuotaMensual(dto.getCuotaMensual());

        MetaMensual nueva = repository.save(meta);

        return new MetaMensualDTO(
                nueva.getId(),
                nueva.getUsuarioId(),
                nueva.getNombre(),
                nueva.getCuotaMensual()
        );
    }

    public MetaMensualDTO updateMeta(Integer id, MetaMensualDTO dto) {
        MetaMensual existe = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Meta no encontrada"));

        try {
            UsuarioDTO usuarioExterna = usuarioClient.obtenerUsuario(dto.getUsuarioId());
            if (usuarioExterna == null) {
                throw new RuntimeException("El usuario no existe");
            }
        } catch (Exception e) {
            throw new RuntimeException("Usuario no disponible");
        }

        existe.setUsuarioId(dto.getUsuarioId());
        existe.setNombre(dto.getNombre());
        existe.setCuotaMensual(dto.getCuotaMensual());

        MetaMensual nueva = repository.save(existe);

        return new MetaMensualDTO(
                nueva.getId(),
                nueva.getUsuarioId(),
                nueva.getNombre(),
                nueva.getCuotaMensual()
        );
    }

    public void deleteMeta(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Meta no encontrada");
        }
    }
}