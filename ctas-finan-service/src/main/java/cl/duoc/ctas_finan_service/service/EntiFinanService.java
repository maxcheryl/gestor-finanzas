package cl.duoc.ctas_finan_service.service;

import cl.duoc.ctas_finan_service.client.UsuarioClient;
import cl.duoc.ctas_finan_service.dto.EntiFinanDTO;
import cl.duoc.ctas_finan_service.dto.EntiFinanResponseDTO;
import cl.duoc.ctas_finan_service.dto.MetodoPagoDTO;
import cl.duoc.ctas_finan_service.dto.UsuarioDTO;
import cl.duoc.ctas_finan_service.model.EntiFinan;
import cl.duoc.ctas_finan_service.model.MetodoPago;
import cl.duoc.ctas_finan_service.repository.EntiFinanRepository;
import cl.duoc.ctas_finan_service.repository.MetodoPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntiFinanService {

    @Autowired
    private EntiFinanRepository repository;

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    @Autowired
    private UsuarioClient usuarioClient;

    // LISTAR
    public List<EntiFinanResponseDTO> getEntidadesFinancieras() {

        return repository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    // BUSCAR POR ID
    public Optional<EntiFinanResponseDTO> getEntiFinan(Integer id) {

        return repository.findById(id)
                .map(this::toResponseDTO);
    }

    // GUARDAR
    public EntiFinanDTO saveEntiFinan(
            EntiFinanDTO dto
    ) {

        EntiFinan entidad =
                new EntiFinan();

        entidad.setUsuarioId(
                dto.getUsuarioId());

        entidad.setNombreEntidad(
                dto.getNombreEntidad());

        entidad.setMetodoPagoId(
                dto.getMetodoPagoId());

        EntiFinan guardado =
                repository.save(entidad);

        return new EntiFinanDTO(
                guardado.getId(),
                guardado.getUsuarioId(),
                guardado.getNombreEntidad(),
                guardado.getMetodoPagoId()
        );
    }

    // ACTUALIZAR
    public EntiFinanDTO updateEntiFinan(
            Integer id,
            EntiFinanDTO dto
    ) {

        EntiFinan existe =
                repository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Entidad financiera no encontrada"
                                ));

        existe.setUsuarioId(
                dto.getUsuarioId());

        existe.setNombreEntidad(
                dto.getNombreEntidad());

        existe.setMetodoPagoId(
                dto.getMetodoPagoId());

        EntiFinan actualizado =
                repository.save(existe);

        return new EntiFinanDTO(
                actualizado.getId(),
                actualizado.getUsuarioId(),
                actualizado.getNombreEntidad(),
                actualizado.getMetodoPagoId()
        );
    }

    // ELIMINAR
    public void deleteEntiFinan(
            Integer id
    ) {

        if (repository.existsById(id)) {

            repository.deleteById(id);

        } else {

            throw new RuntimeException(
                    "Entidad financiera no encontrada"
            );
        }
    }

    private EntiFinanResponseDTO toResponseDTO(EntiFinan entidad) {

        UsuarioDTO usuario = null;

        try {

            usuario =
                    usuarioClient.obtenerUsuario(
                            entidad.getUsuarioId()
                    );

        } catch (Exception e) {

            usuario =
                    new UsuarioDTO(
                            entidad.getUsuarioId(),
                            "Usuario no disponible"
                    );
        }

        MetodoPagoDTO metodoPago =
                metodoPagoRepository.findById(
                                entidad.getMetodoPagoId()
                        )
                        .map(this::toMetodoPagoDTO)
                        .orElse(
                                new MetodoPagoDTO(
                                        entidad.getMetodoPagoId(),
                                        "Metodo de pago no disponible"
                                )
                        );

        return new EntiFinanResponseDTO(
                entidad.getId(),
                usuario,
                entidad.getNombreEntidad(),
                metodoPago
        );
    }

    private MetodoPagoDTO toMetodoPagoDTO(MetodoPago metodoPago) {

        return new MetodoPagoDTO(
                metodoPago.getId(),
                metodoPago.getNombreMetodo()
        );
    }
}
