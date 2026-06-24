package cl.duoc.reportes_service.controller;

import cl.duoc.reportes_service.dto.ReporteMensualDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import cl.duoc.reportes_service.dto.ReporteMensualResponseDTO;
import cl.duoc.reportes_service.service.ReporteMensualService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Reportes mensuales", description = "Operaciones relacionadas con reportes mensuales financieros")
@RestController
@RequestMapping("/api/v1/reportes")
public class ReporteMensualController {

    @Autowired
    private ReporteMensualService service;

    // LISTAR
    @Operation(summary = "Listar reportes", description = "Retorna todos los reportes mensuales registrados")
    @GetMapping
    public ResponseEntity<List<ReporteMensualResponseDTO>>
    listar() {

        List<ReporteMensualResponseDTO> reportes =
                service.getReportes();

        if (reportes.isEmpty()) {

            return ResponseEntity
                    .noContent()
                    .build();
        }

        return ResponseEntity.ok(
                reportes
        );
    }

    // BUSCAR POR ID
    @Operation(summary = "Buscar reporte por ID", description = "Retorna un reporte mensual segun su identificador")
    @GetMapping("/{id}")
    public ResponseEntity<ReporteMensualResponseDTO>
    buscarPorId(
            @PathVariable Integer id
    ) {

        Optional<ReporteMensualResponseDTO> reporte =
                service.getReporte(id);

        return reporte
                .map(ResponseEntity::ok)
                .orElse(
                        ResponseEntity
                                .notFound()
                                .build()
                );
    }

    // GUARDAR
    @Operation(summary = "Crear reporte", description = "Registra un nuevo reporte mensual")
    @PostMapping
    public ResponseEntity<ReporteMensualDTO> guardar(
            @Valid @RequestBody ReporteMensualDTO dto
    ) {

        ReporteMensualDTO nuevo =
                service.saveReporte(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevo);
    }

    // ACTUALIZAR
    @Operation(summary = "Actualizar reporte", description = "Modifica un reporte mensual existente")
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(
            @PathVariable Integer id,
            @Valid @RequestBody ReporteMensualDTO dto
    ) {

        try {

            ReporteMensualDTO actualizado =
                    service.updateReporte(
                            id,
                            dto
                    );

            return ResponseEntity.ok(
                    actualizado
            );

        } catch (RuntimeException e) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    // ELIMINAR
    @Operation(summary = "Eliminar reporte", description = "Elimina un reporte mensual por su identificador")
    @DeleteMapping("/{id}")
    public ResponseEntity<String>
    eliminar(
            @PathVariable Integer id
    ) {

        try {

            service.deleteReporte(id);

            return ResponseEntity
                    .ok(
                            "Reporte eliminado correctamente"
                    );

        } catch (RuntimeException e) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
}
