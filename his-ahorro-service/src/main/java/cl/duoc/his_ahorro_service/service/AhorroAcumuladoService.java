package cl.duoc.his_ahorro_service.service;

import cl.duoc.his_ahorro_service.client.MetaMensualClient;
import cl.duoc.his_ahorro_service.client.UsuarioClient;
import cl.duoc.his_ahorro_service.dto.AhorroAcumuladoDTO;
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

    // LISTAR
    public List<AhorroAcumulado> getAhorros() {

        return repository.findAll();
    }

    // BUSCAR POR ID
    public Optional<AhorroAcumulado>
    getAhorro(Integer id) {

        return repository.findById(id);
    }

    // ACUMULAR AHORRO
    public AhorroAcumulado saveAhorro(
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

            UsuarioDTO usuario =
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

        // SI EXISTE → ACUMULAR
        if (existente.isPresent()) {

            ahorro = existente.get();

            Double nuevoSaldo =
                    ahorro.getSaldoTotalAhorrado()
                            + cuotaMensual;

            ahorro.setSaldoTotalAhorrado(
                    nuevoSaldo
            );

        } else {

            // SI NO EXISTE → CREAR
            ahorro = new AhorroAcumulado();

            ahorro.setMetaId(dto.getMetaId());
            ahorro.setUsuarioId(dto.getUsuarioId());

            ahorro.setSaldoTotalAhorrado(
                    cuotaMensual
            );
        }

        return repository.save(ahorro);
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