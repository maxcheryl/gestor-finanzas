package cl.duoc.reportes_service.controller;

import cl.duoc.reportes_service.dto.DetalleReporteDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import cl.duoc.reportes_service.dto.DetalleReporteResponseDTO;
import cl.duoc.reportes_service.service.DetalleReporteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Tag(name = "Detalles de reportes", description = "Operaciones relacionadas con detalles de reportes mensuales")
@RestController
@RequestMapping("/api/v1/detalles")
public class DetalleReporteController {

    @Autowired
    private DetalleReporteService service;

    // LISTAR
    @Operation(summary = "Listar detalles", description = "Retorna todos los detalles de reportes registrados")
    @GetMapping
    public ResponseEntity<List<DetalleReporteResponseDTO>>
    listar() {

        List<DetalleReporteResponseDTO> detalles =
                service.getDetalles();

        if (detalles.isEmpty()) {

            return ResponseEntity
                    .noContent()
                    .build();
        }

        return ResponseEntity.ok(
                detalles
        );
    }

    // BUSCAR POR ID
    @Operation(summary = "Buscar detalle por ID", description = "Retorna un detalle de reporte segun su identificador")
    @GetMapping("/{id}")
    public EntityModel<DetalleReporteResponseDTO>
    buscarPorId(
            @PathVariable Integer id
    ) {

        DetalleReporteResponseDTO detalle =
                service.getDetalle(id).orElseThrow();

        EntityModel<DetalleReporteResponseDTO> model = EntityModel.of(detalle);

        model.add(
                linkTo(
                        methodOn(DetalleReporteController.class).buscarPorId(id)
                ).withSelfRel()
        );

        model.add(
                linkTo(
                        methodOn(DetalleReporteController.class).listar()
                ).withRel("Todos-los-detalles")
        );

        return model;
    }

    // GUARDAR
    @Operation(summary = "Crear detalle", description = "Registra un nuevo detalle para un reporte mensual")
    @PostMapping
    public ResponseEntity<DetalleReporteDTO> guardar(
            @Valid @RequestBody DetalleReporteDTO dto
    ) {

        DetalleReporteDTO nuevo =
                service.saveDetalle(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevo);
    }

    // ACTUALIZAR
    @Operation(summary = "Actualizar detalle", description = "Modifica un detalle de reporte existente")
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(
            @PathVariable Integer id,
            @Valid @RequestBody DetalleReporteDTO dto
    ) {

        try {

            DetalleReporteDTO actualizado =
                    service.updateDetalle(
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
    @Operation(summary = "Eliminar detalle", description = "Elimina un detalle de reporte por su identificador")
    @DeleteMapping("/{id}")
    public ResponseEntity<String>
    eliminar(
            @PathVariable Integer id
    ) {

        try {

            service.deleteDetalle(id);

            return ResponseEntity
                    .ok(
                            "Detalle eliminado correctamente"
                    );

        } catch (RuntimeException e) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }


}
