package cl.duoc.his_ahorro_service.service;

import cl.duoc.his_ahorro_service.client.MetaMensualClient;
import cl.duoc.his_ahorro_service.client.UsuarioClient;
import cl.duoc.his_ahorro_service.dto.AhorroAcumuladoDTO;
import cl.duoc.his_ahorro_service.dto.AhorroAcumuladoResponseDTO;
import cl.duoc.his_ahorro_service.dto.MetaMensualDTO;
import cl.duoc.his_ahorro_service.dto.UsuarioDTO;
import cl.duoc.his_ahorro_service.model.AhorroAcumulado;
import cl.duoc.his_ahorro_service.repository.AhorroAcumuladoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AhorroAcumuladoService {

    @Autowired
    private AhorroAcumuladoRepository repository;

    @Autowired
    private MetaMensualClient metaMensualClient;

    @Autowired
    private UsuarioClient usuarioClient;

    // MAPPER ENTITY -> RESPONSE DTO
    private AhorroAcumuladoResponseDTO toResponseDTO(
            AhorroAcumulado ahorro
    ) {

        UsuarioDTO usuario =
                usuarioClient.obtenerUsuario(
                        ahorro.getUsuarioId()
                );

        MetaMensualDTO meta =
                metaMensualClient.obtenerMeta(
                        ahorro.getMetaId()
                );

        return new AhorroAcumuladoResponseDTO(
                ahorro.getId(),
                usuario,
                meta,
                ahorro.getSaldoTotalAhorrado()
        );
    }

    // LISTAR
    public List<AhorroAcumuladoResponseDTO> getAhorros() {

        return repository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    // BUSCAR POR ID
    public Optional<AhorroAcumuladoResponseDTO> getAhorro(Integer id) {

        return repository.findById(id)
                .map(this::toResponseDTO);
    }

    // ACUMULAR AHORRO
    public AhorroAcumuladoResponseDTO saveAhorro(
            AhorroAcumuladoDTO dto
    ) {

        // VALIDAR META
        MetaMensualDTO meta;

        try {

            meta =
                    metaMensualClient.obtenerMeta(
                            dto.getMetaId()
                    );

        } catch (Exception e) {

            throw new RuntimeException(
                    "La meta no existe."
            );
        }

        // VALIDAR USUARIO
        try {

            usuarioClient.obtenerUsuario(
                    dto.getUsuarioId()
            );

        } catch (Exception e) {

            throw new RuntimeException(
                    "El usuario no existe."
            );
        }

        // VALIDAR PERTENENCIA
        if (!meta.getUsuarioId().equals(
                dto.getUsuarioId()
        )) {

            throw new RuntimeException(
                    "La meta no pertenece al usuario."
            );
        }

        // OBTENER CUOTA
        Double cuotaMensual =
                meta.getCuotaMensual();

        // BUSCAR AHORRO EXISTENTE
        Optional<AhorroAcumulado> existente =
                repository.findByMetaId(
                        dto.getMetaId()
                );

        AhorroAcumulado ahorro;

        // SI EXISTE -> ACUMULAR
        if (existente.isPresent()) {

            ahorro = existente.get();

            Double nuevoSaldo =
                    ahorro.getSaldoTotalAhorrado()
                            + cuotaMensual;

            ahorro.setSaldoTotalAhorrado(
                    nuevoSaldo
            );

        } else {

            // SI NO EXISTE -> CREAR
            ahorro = new AhorroAcumulado();

            ahorro.setMetaId(
                    dto.getMetaId()
            );

            ahorro.setUsuarioId(
                    dto.getUsuarioId()
            );

            ahorro.setSaldoTotalAhorrado(
                    cuotaMensual
            );
        }

        AhorroAcumulado guardado =
                repository.save(
                        ahorro
                );

        return toResponseDTO(
                guardado
        );
    }

    // ELIMINAR
    public void deleteAhorro(Integer id) {

        if (repository.existsById(id)) {

            repository.deleteById(id);

        } else {

            throw new RuntimeException(
                    "Ahorro no encontrado"
            );
        }
    }
}