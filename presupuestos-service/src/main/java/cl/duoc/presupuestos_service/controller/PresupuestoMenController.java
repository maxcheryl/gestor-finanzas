package cl.duoc.presupuestos_service.controller;

import cl.duoc.presupuestos_service.dto.PresupuestoMenDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import cl.duoc.presupuestos_service.dto.PresupuestoResponseDTO;
import cl.duoc.presupuestos_service.service.PresupuestoMenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Tag(name = "Presupuestos mensuales", description = "Operaciones relacionadas con presupuestos mensuales de usuarios")
@RestController
@RequestMapping("/api/v1/presupuestomen")
public class PresupuestoMenController {

    @Autowired
    private PresupuestoMenService presuMenService;

    @Operation(summary = "Listar presupuestos", description = "Retorna todos los presupuestos mensuales registrados")
    @GetMapping
    public ResponseEntity<List<PresupuestoMenDTO>> listar() {
        List<PresupuestoMenDTO> presupuestos =
                presuMenService.getPresupuestos();

        if (presupuestos.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204
        }

        return ResponseEntity.ok(presupuestos); // 200
    }

    @Operation(summary = "Buscar presupuesto por ID", description = "Retorna un presupuesto mensual segun su identificador")
    @GetMapping("/{id}")
    public ResponseEntity<PresupuestoMenDTO> buscarPorId(
            @PathVariable Integer id
    ) {
        Optional<PresupuestoMenDTO> presupuesto =
                presuMenService.getPresupuesto(id);

        return presupuesto
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build()); // 404
    }

    @Operation(summary = "Crear presupuesto", description = "Registra un nuevo presupuesto mensual")
    @PostMapping
    public ResponseEntity<?> guardar(
            @Valid @RequestBody PresupuestoMenDTO dto,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(Map.of("mensaje", result.getFieldError().getDefaultMessage()));
        }
        PresupuestoMenDTO nuevo =
                presuMenService.savePresupuesto(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevo); // 201
    }

    @Operation(summary = "Actualizar presupuesto", description = "Modifica un presupuesto mensual existente")
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(
            @PathVariable Integer id,
            @Valid @RequestBody PresupuestoMenDTO dto,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(Map.of("mensaje", result.getFieldError().getDefaultMessage()));
        }
        try {
            PresupuestoMenDTO actualizado =
                    presuMenService.updatePresupuesto(id, dto);

            return ResponseEntity.ok(actualizado); // 200

        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @Operation(summary = "Eliminar presupuesto", description = "Elimina un presupuesto mensual por su identificador")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(
            @PathVariable Integer id
    ) {
        try {
            presuMenService.deletePresupuesto(id);
            return ResponseEntity.ok("Presupuesto eliminado correctamente");

        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @Operation(summary = "Buscar presupuesto con usuario", description = "Retorna un presupuesto mensual junto con el usuario asociado y monto disponible calculado")
    @GetMapping("/{id}/usuario")
    public ResponseEntity<PresupuestoResponseDTO> buscarConUsuario(
            @PathVariable Integer id
    ) {
        try {
            PresupuestoResponseDTO respuesta =
                    presuMenService.getPresupuestoConUsuario(id);
            return ResponseEntity.ok(respuesta); // 200
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // 404
        }
    }

    @Operation(summary = "Listar presupuestos con usuario", description = "Retorna todos los presupuestos mensuales junto con sus usuarios asociados")
    @GetMapping("/con-usuario")
    public ResponseEntity<List<PresupuestoResponseDTO>> listarConUsuario() {
        List<PresupuestoResponseDTO> presupuestos =
                presuMenService.getPresupuestosConUsuario();

        if (presupuestos.isEmpty())
            return ResponseEntity.noContent().build(); // 204

        return ResponseEntity.ok(presupuestos); // 200
    }
}
