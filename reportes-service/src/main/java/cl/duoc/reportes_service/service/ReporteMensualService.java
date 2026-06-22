package cl.duoc.reportes_service.service;

import cl.duoc.reportes_service.client.UsuarioClient;
import cl.duoc.reportes_service.dto.ReporteMensualDTO;
import cl.duoc.reportes_service.dto.ReporteMensualResponseDTO;
import cl.duoc.reportes_service.dto.UsuarioDTO;
import cl.duoc.reportes_service.model.ReporteMensual;
import cl.duoc.reportes_service.repository.ReporteMensualRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReporteMensualService {

    @Autowired
    private ReporteMensualRepository repository;

    @Autowired
    private UsuarioClient usuarioClient;

    // LISTAR
    public List<ReporteMensualResponseDTO>
    getReportes() {

        return repository.findAll()
                .stream()
                .map(reporte -> {

                    UsuarioDTO usuario = null;

                    try {

                        usuario =
                                usuarioClient.obtenerUsuario(
                                        reporte.getUsuarioId()
                                );

                    } catch (Exception e) {

                        usuario =
                                new UsuarioDTO(
                                        reporte.getUsuarioId(),
                                        "Usuario no disponible"
                                );
                    }

                    return new ReporteMensualResponseDTO(
                            reporte.getId(),
                            usuario,
                            reporte.getFecha(),
                            reporte.getIngresosTotales(),
                            reporte.getGastosTotales(),
                            reporte.getSaldoNeto()
                    );
                })
                .toList();
    }

    // BUSCAR POR ID
    public Optional<ReporteMensualResponseDTO>
    getReporte(Integer id) {

        return repository.findById(id)
                .map(reporte -> {

                    UsuarioDTO usuario = null;

                    try {

                        usuario =
                                usuarioClient.obtenerUsuario(
                                        reporte.getUsuarioId()
                                );

                    } catch (Exception e) {

                        usuario =
                                new UsuarioDTO(
                                        reporte.getUsuarioId(),
                                        "Usuario no disponible"
                                );
                    }

                    return new ReporteMensualResponseDTO(
                            reporte.getId(),
                            usuario,
                            reporte.getFecha(),
                            reporte.getIngresosTotales(),
                            reporte.getGastosTotales(),
                            reporte.getSaldoNeto()
                    );
                });
    }

    // GUARDAR
    public ReporteMensualDTO saveReporte(
            ReporteMensualDTO dto
    ) {

        try {

            UsuarioDTO usuarioExterno =
                    usuarioClient.obtenerUsuario(
                            dto.getUsuarioId()
                    );

            if (usuarioExterno == null) {

                throw new RuntimeException(
                        "El usuario no existe."
                );
            }

        } catch (Exception e) {

            throw new RuntimeException(
                    "Usuario no disponible"
            );
        }

        ReporteMensual reporte =
                new ReporteMensual();

        reporte.setUsuarioId(dto.getUsuarioId());
        reporte.setFecha(dto.getFecha());
        reporte.setIngresosTotales(
                dto.getIngresosTotales()
        );
        reporte.setGastosTotales(
                dto.getGastosTotales()
        );
        reporte.setSaldoNeto(
                dto.getSaldoNeto()
        );

        ReporteMensual nuevo =
                repository.save(reporte);

        return new ReporteMensualDTO(
                nuevo.getId(),
                nuevo.getUsuarioId(),
                nuevo.getFecha(),
                nuevo.getIngresosTotales(),
                nuevo.getGastosTotales(),
                nuevo.getSaldoNeto()
        );
    }

    // ACTUALIZAR
    public ReporteMensualDTO updateReporte(
            Integer id,
            ReporteMensualDTO dto
    ) {

        ReporteMensual existe =
                repository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Reporte no encontrado"
                                ));

        try {

            UsuarioDTO usuarioExterno =
                    usuarioClient.obtenerUsuario(
                            dto.getUsuarioId()
                    );

            if (usuarioExterno == null) {

                throw new RuntimeException(
                        "El usuario no existe."
                );
            }

        } catch (Exception e) {

            throw new RuntimeException(
                    "No se pudo validar el usuario."
            );
        }

        existe.setUsuarioId(dto.getUsuarioId());
        existe.setFecha(dto.getFecha());
        existe.setIngresosTotales(
                dto.getIngresosTotales()
        );
        existe.setGastosTotales(
                dto.getGastosTotales()
        );
        existe.setSaldoNeto(
                dto.getSaldoNeto()
        );

        ReporteMensual actualizado =
                repository.save(existe);

        return new ReporteMensualDTO(
                actualizado.getId(),
                actualizado.getUsuarioId(),
                actualizado.getFecha(),
                actualizado.getIngresosTotales(),
                actualizado.getGastosTotales(),
                actualizado.getSaldoNeto()
        );
    }

    // ELIMINAR
    public void deleteReporte(Integer id) {

        if (repository.existsById(id)) {

            repository.deleteById(id);

        } else {

            throw new RuntimeException(
                    "Reporte no encontrado"
            );
        }
    }
}