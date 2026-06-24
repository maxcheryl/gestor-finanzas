package cl.duoc.metas_service.controller;

import cl.duoc.metas_service.dto.MetaMensualDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import cl.duoc.metas_service.dto.MetaMensualResponseDTO;
import cl.duoc.metas_service.service.MetaMensualService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Metas mensuales", description = "Operaciones relacionadas con metas mensuales de ahorro")
@RestController
@RequestMapping("/api/v1/metas")
public class MetaMensualController {

    @Autowired
    private MetaMensualService metaService;

    @Operation(summary = "Listar metas", description = "Retorna todas las metas mensuales registradas")
    @GetMapping
    public ResponseEntity<List<MetaMensualResponseDTO>> listar() {

        List<MetaMensualResponseDTO> metas =
                metaService.getMetas();

        if (metas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(metas);
    }

    @Operation(summary = "Buscar meta por ID", description = "Retorna una meta mensual segun su identificador")
    @GetMapping("/{id}")
    public ResponseEntity<MetaMensualResponseDTO> buscarPorId(
            @PathVariable Integer id
    ) {

        Optional<MetaMensualResponseDTO> meta =
                metaService.getMeta(id);

        return meta
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear meta", description = "Registra una nueva meta mensual")
    @PostMapping
    public ResponseEntity<MetaMensualDTO> guardar(
            @Valid @RequestBody MetaMensualDTO dto
    ) {

        MetaMensualDTO nueva =
                metaService.saveMeta(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nueva);
    }

    @Operation(summary = "Actualizar meta", description = "Modifica una meta mensual existente")
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(
            @PathVariable Integer id,
            @Valid @RequestBody MetaMensualDTO dto
    ) {

        try {
            MetaMensualDTO actualizada =
                    metaService.updateMeta(id, dto);

            return ResponseEntity.ok(actualizada);

        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @Operation(summary = "Eliminar meta", description = "Elimina una meta mensual por su identificador")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(
            @PathVariable Integer id
    ) {

        try {
            metaService.deleteMeta(id);
            return ResponseEntity.ok("Meta eliminada correctamente");

        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
}
