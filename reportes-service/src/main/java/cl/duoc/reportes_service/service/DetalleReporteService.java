package cl.duoc.reportes_service.service;

import cl.duoc.reportes_service.client.CategoriaClient;
import cl.duoc.reportes_service.dto.CategoriaDTO;
import cl.duoc.reportes_service.dto.DetalleReporteDTO;
import cl.duoc.reportes_service.dto.DetalleReporteResponseDTO;
import cl.duoc.reportes_service.dto.ReporteMensualResponseDTO;
import cl.duoc.reportes_service.model.DetalleReporte;
import cl.duoc.reportes_service.repository.DetalleReporteRepository;
import cl.duoc.reportes_service.repository.ReporteMensualRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleReporteService {

    @Autowired
    private DetalleReporteRepository repository;

    @Autowired
    private ReporteMensualRepository reporteRepository;

    @Autowired
    private CategoriaClient categoriaClient;

    @Autowired
    private ReporteMensualService reporteMensualService;

    // LISTAR
    public List<DetalleReporteResponseDTO>
    getDetalles() {

        return repository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    // BUSCAR POR ID
    public Optional<DetalleReporteResponseDTO>
    getDetalle(Integer id) {

        return repository.findById(id)
                .map(this::toResponseDTO);
    }

    // GUARDAR
    public DetalleReporteDTO saveDetalle(
            DetalleReporteDTO dto
    ) {

        // VALIDAR REPORTE
        if (!reporteRepository.existsById(
                dto.getReporteId()
        )) {

            throw new RuntimeException(
                    "El reporte no existe."
            );
        }

        // VALIDAR CATEGORIA
        try {

            CategoriaDTO categoriaExterna =
                    categoriaClient.obtenerCategoria(
                            dto.getCategoriaId()
                    );

            if (categoriaExterna == null) {

                throw new RuntimeException(
                        "La categoría no existe."
                );
            }

        } catch (Exception e) {

            throw new RuntimeException(
                    "Categoría no disponible"
            );
        }

        DetalleReporte detalle =
                new DetalleReporte();

        detalle.setReporteId(dto.getReporteId());
        detalle.setCategoriaId(dto.getCategoriaId());
        detalle.setTotalGastado(
                dto.getTotalGastado()
        );

        DetalleReporte nuevo =
                repository.save(detalle);

        return new DetalleReporteDTO(
                nuevo.getId(),
                nuevo.getReporteId(),
                nuevo.getCategoriaId(),
                nuevo.getTotalGastado()
        );
    }

    // ACTUALIZAR
    public DetalleReporteDTO updateDetalle(
            Integer id,
            DetalleReporteDTO dto
    ) {

        DetalleReporte existe =
                repository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Detalle no encontrado"
                                ));

        // VALIDAR REPORTE
        if (!reporteRepository.existsById(
                dto.getReporteId()
        )) {

            throw new RuntimeException(
                    "El reporte no existe."
            );
        }

        // VALIDAR CATEGORIA
        try {

            CategoriaDTO categoriaExterna =
                    categoriaClient.obtenerCategoria(
                            dto.getCategoriaId()
                    );

            if (categoriaExterna == null) {

                throw new RuntimeException(
                        "La categoría no existe."
                );
            }

        } catch (Exception e) {

            throw new RuntimeException(
                    "No se pudo validar la categoría."
            );
        }

        existe.setReporteId(dto.getReporteId());
        existe.setCategoriaId(dto.getCategoriaId());
        existe.setTotalGastado(
                dto.getTotalGastado()
        );

        DetalleReporte actualizado =
                repository.save(existe);

        return new DetalleReporteDTO(
                actualizado.getId(),
                actualizado.getReporteId(),
                actualizado.getCategoriaId(),
                actualizado.getTotalGastado()
        );
    }

    // ELIMINAR
    public void deleteDetalle(Integer id) {

        if (repository.existsById(id)) {

            repository.deleteById(id);

        } else {

            throw new RuntimeException(
                    "Detalle no encontrado"
            );
        }
    }

    private DetalleReporteResponseDTO toResponseDTO(DetalleReporte detalle) {

        ReporteMensualResponseDTO reporte =
                reporteMensualService.getReporte(detalle.getReporteId())
                        .orElse(null);

        CategoriaDTO categoria = null;

        try {

            categoria =
                    categoriaClient.obtenerCategoria(
                            detalle.getCategoriaId()
                    );

            if (categoria != null && categoria.getId() == null) {
                categoria.setId(detalle.getCategoriaId());
            }

        } catch (Exception e) {

            categoria =
                    new CategoriaDTO(
                            detalle.getCategoriaId(),
                            "Categoria no disponible",
                            null
                    );
        }

        return new DetalleReporteResponseDTO(
                detalle.getId(),
                reporte,
                categoria,
                detalle.getTotalGastado()
        );
    }
}
