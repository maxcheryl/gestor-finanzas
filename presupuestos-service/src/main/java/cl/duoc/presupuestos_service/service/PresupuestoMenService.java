package cl.duoc.presupuestos_service.service;

import cl.duoc.presupuestos_service.client.GastoClient;
import cl.duoc.presupuestos_service.client.UsuarioClient;
import cl.duoc.presupuestos_service.dto.GastoDTO;
import cl.duoc.presupuestos_service.dto.PresupuestoMenDTO;
import cl.duoc.presupuestos_service.dto.PresupuestoResponseDTO;
import cl.duoc.presupuestos_service.dto.UsuarioDTO;
import cl.duoc.presupuestos_service.model.PresupuestoMen;
import cl.duoc.presupuestos_service.repository.PresupuestoMenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PresupuestoMenService {

    @Autowired
    private PresupuestoMenRepository presuMenRepository;

    // LISTAR
    public List<PresupuestoMenDTO> getPresupuestos() {
        return presuMenRepository.findAll()
                .stream()
                .map(presu ->
                        new PresupuestoMenDTO(
                                presu.getUsuarioId(),
                                presu.getMes(),
                                presu.getAnio(),
                                presu.getMontoInicial()
                        )
                )
                .toList();
    }

    // BUSCAR POR ID
    public Optional<PresupuestoMenDTO> getPresupuesto(Integer id) {
        return presuMenRepository.findById(id)
                .map(presu ->
                        new PresupuestoMenDTO(
                                presu.getUsuarioId(),
                                presu.getMes(),
                                presu.getAnio(),
                                presu.getMontoInicial()
                        )
                );
    }

    // GUARDAR
    public PresupuestoMenDTO savePresupuesto(PresupuestoMenDTO dto) {
        PresupuestoMen presu = new PresupuestoMen();
        presu.setUsuarioId(dto.getUsuarioId());
        presu.setMes(dto.getMes());
        presu.setAnio(dto.getAnio());
        presu.setMontoInicial(dto.getMontoInicial());
        presu.setMontoActualDisp(dto.getMontoInicial()); // se calcula automáticamente

        PresupuestoMen guardado = presuMenRepository.save(presu);

        return new PresupuestoMenDTO(
                guardado.getUsuarioId(),
                guardado.getMes(),
                guardado.getAnio(),
                guardado.getMontoInicial()
        );
    }

    // ACTUALIZAR
    public PresupuestoMenDTO updatePresupuesto(Integer id, PresupuestoMenDTO dto) {
        PresupuestoMen existe = presuMenRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Presupuesto no encontrado"));

        existe.setUsuarioId(dto.getUsuarioId());
        existe.setMes(dto.getMes());
        existe.setAnio(dto.getAnio());
        existe.setMontoInicial(dto.getMontoInicial());

        PresupuestoMen actualizado = presuMenRepository.save(existe);

        return new PresupuestoMenDTO(
                actualizado.getUsuarioId(),
                actualizado.getMes(),
                actualizado.getAnio(),
                actualizado.getMontoInicial()
        );
    }

    // ELIMINAR
    public void deletePresupuesto(Integer id) {
        if (presuMenRepository.existsById(id)) {
            presuMenRepository.deleteById(id);
        } else {
            throw new RuntimeException("Presupuesto no encontrado");
        }
    }

    @Autowired
    private UsuarioClient usuarioClient;


    @Autowired
    private GastoClient gastoClient;

    public PresupuestoResponseDTO getPresupuestoConUsuario(Integer id) {
        PresupuestoMen presu = presuMenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Presupuesto no encontrado"));

        UsuarioDTO usuario = usuarioClient.obtenerUsuario(presu.getUsuarioId());

        Integer montoActual = presu.getMontoInicial();

        try {
            List<GastoDTO> gastos = gastoClient.obtenerGastos();

            Double totalGastos = gastos.stream()
                    .filter(g -> g.getUsuarioId().equals(presu.getUsuarioId())
                            && g.getFecha().getMonthValue() == presu.getMes()
                            && g.getFecha().getYear() == presu.getAnio())
                    .mapToDouble(GastoDTO::getMonto)
                    .sum();

            montoActual = presu.getMontoInicial() - totalGastos.intValue();

        } catch (Exception e) {
            System.out.println("Error al obtener gastos: " + e.getMessage());
        }

        return new PresupuestoResponseDTO(
                presu.getId(),
                usuario,
                presu.getMes(),
                presu.getAnio(),
                presu.getMontoInicial(),
                montoActual
        );
    }

    public List<PresupuestoResponseDTO> getPresupuestosConUsuario() {
        List<PresupuestoMen> presupuestos = presuMenRepository.findAll();

        List<GastoDTO> todosGastos;
        try {
            todosGastos = gastoClient.obtenerGastos();
        } catch (Exception e) {
            System.out.println("Error al obtener gastos: " + e.getMessage());
            todosGastos = new ArrayList<>();
        }

        List<GastoDTO> gastosFinal = todosGastos;

        return presupuestos.stream()
                .map(presu -> {
                    UsuarioDTO usuario = usuarioClient.obtenerUsuario(presu.getUsuarioId());

                    Double totalGastos = gastosFinal.stream()
                            .filter(g -> g.getUsuarioId().equals(presu.getUsuarioId())
                                    && g.getFecha().getMonthValue() == presu.getMes()
                                    && g.getFecha().getYear() == presu.getAnio())
                            .mapToDouble(GastoDTO::getMonto)
                            .sum();

                    Integer montoActual = presu.getMontoInicial() - totalGastos.intValue();

                    return new PresupuestoResponseDTO(
                            presu.getId(),
                            usuario,
                            presu.getMes(),
                            presu.getAnio(),
                            presu.getMontoInicial(),
                            montoActual
                    );
                })
                .toList();
    }
}