package cl.duoc.ctas_finan_service.controller;

import cl.duoc.ctas_finan_service.dto.EntiFinanDTO;
import cl.duoc.ctas_finan_service.dto.EntiFinanResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import cl.duoc.ctas_finan_service.service.EntiFinanService;
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

@Tag(name = "Entidades financieras", description = "Operaciones relacionadas con entidades financieras de usuarios")
@RestController
@RequestMapping("/api/v1/entidades-financieras")
public class EntiFinanController {

    @Autowired
    private EntiFinanService service;

    @Operation(summary = "Listar entidades financieras", description = "Retorna todas las entidades financieras registradas")
    @GetMapping
    public ResponseEntity<List<EntiFinanResponseDTO>>
    listar() {

        List<EntiFinanResponseDTO> entidades =
                service.getEntidadesFinancieras();

        if (entidades.isEmpty()) {
            return ResponseEntity
                    .noContent()
                    .build();
        }

        return ResponseEntity.ok(
                entidades
        );
    }

    @Operation(summary = "Buscar entidad financiera por ID", description = "Retorna una entidad financiera segun su identificador")
    @GetMapping("/{id}")
    public EntityModel<EntiFinanResponseDTO>
    buscarPorId(
            @PathVariable Integer id
    ) {

        EntiFinanResponseDTO entidad =
                service.getEntiFinan(id).orElseThrow();

        EntityModel<EntiFinanResponseDTO> model = EntityModel.of(entidad);

        model.add(
                linkTo(
                        methodOn(EntiFinanController.class).buscarPorId(id)
                ).withSelfRel()
        );

        model.add(
                linkTo(
                        methodOn(EntiFinanController.class).listar()
                ).withRel("Todas-las-entidades")
        );

        return model;
    }

    @Operation(summary = "Crear entidad financiera", description = "Registra una nueva entidad financiera")
    @PostMapping
    public ResponseEntity<EntiFinanDTO> guardar(
            @Valid @RequestBody EntiFinanDTO dto
    ) {

        EntiFinanDTO nueva =
                service.saveEntiFinan(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nueva);
    }

    @Operation(summary = "Actualizar entidad financiera", description = "Modifica una entidad financiera existente")
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(
            @PathVariable Integer id,
            @Valid @RequestBody EntiFinanDTO dto
    ) {

        try {

            EntiFinanDTO actualizada =
                    service.updateEntiFinan(
                            id,
                            dto
                    );

            return ResponseEntity.ok(
                    actualizada
            );

        } catch (RuntimeException e) {

            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    @Operation(summary = "Eliminar entidad financiera", description = "Elimina una entidad financiera por su identificador")
    @DeleteMapping("/{id}")
    public ResponseEntity<String>
    eliminar(
            @PathVariable Integer id
    ) {

        try {

            service.deleteEntiFinan(id);

            return ResponseEntity.ok(
                    "Entidad financiera eliminada correctamente"
            );

        } catch (RuntimeException e) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
}
