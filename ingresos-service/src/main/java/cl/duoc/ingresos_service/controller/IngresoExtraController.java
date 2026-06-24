package cl.duoc.ingresos_service.controller;

import cl.duoc.ingresos_service.dto.IngresoExtraDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import cl.duoc.ingresos_service.dto.IngresoExtraResponseDTO;
import cl.duoc.ingresos_service.service.IngresoExtraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Ingresos extras", description = "Operaciones relacionadas con ingresos extras de usuarios")
@RestController
@RequestMapping("/api/v1/ingresos-extras")
public class IngresoExtraController {

    @Autowired
    private IngresoExtraService ingresoExtraService;

    @Operation(summary = "Listar ingresos extras", description = "Retorna todos los ingresos extras registrados")
    @GetMapping
    public ResponseEntity<List<IngresoExtraResponseDTO>> listar() {

        List<IngresoExtraResponseDTO> ingresos =
                ingresoExtraService.getIngresosExtras();

        if (ingresos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(ingresos);
    }

    @Operation(summary = "Buscar ingreso extra por ID", description = "Retorna un ingreso extra segun su identificador")
    @GetMapping("/{id}")
    public ResponseEntity<IngresoExtraResponseDTO> buscarPorId(
            @PathVariable Integer id
    ) {

        Optional<IngresoExtraResponseDTO> ingreso =
                ingresoExtraService.getIngresoExtra(id);

        return ingreso
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear ingreso extra", description = "Registra un nuevo ingreso extra")
    @PostMapping
    public ResponseEntity<IngresoExtraDTO> guardar(
            @Valid @RequestBody IngresoExtraDTO dto
    ) {

        IngresoExtraDTO nuevo =
                ingresoExtraService.saveIngresoExtra(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevo);
    }

    @Operation(summary = "Actualizar ingreso extra", description = "Modifica un ingreso extra existente")
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(
            @PathVariable Integer id,
            @Valid @RequestBody IngresoExtraDTO dto
    ) {

        try {
            IngresoExtraDTO actualizado =
                    ingresoExtraService.updateIngresoExtra(id, dto);

            return ResponseEntity.ok(actualizado);

        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @Operation(summary = "Eliminar ingreso extra", description = "Elimina un ingreso extra por su identificador")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(
            @PathVariable Integer id
    ) {

        try {
            ingresoExtraService.deleteIngresoExtra(id);
            return ResponseEntity.ok("Ingreso extra eliminado correctamente");

        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
}
