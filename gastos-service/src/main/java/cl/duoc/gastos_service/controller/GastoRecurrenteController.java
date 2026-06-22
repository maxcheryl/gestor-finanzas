package cl.duoc.gastos_service.controller;

import cl.duoc.gastos_service.dto.GastoRecurrenteDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import cl.duoc.gastos_service.dto.GastoRecurrenteResponseDTO;
import cl.duoc.gastos_service.service.GastoRecurrenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Tag(name = "Gastos recurrentes", description = "Operaciones relacionadas con gastos recurrentes de usuarios")
@RestController
@RequestMapping("/api/v1/gastos-recurrentes")
public class GastoRecurrenteController {

    @Autowired
    private GastoRecurrenteService gastoRecurrenteService;

    @Operation(summary = "Listar gastos recurrentes", description = "Retorna todos los gastos recurrentes registrados")
    @GetMapping
    public ResponseEntity<List<GastoRecurrenteResponseDTO>> listar() {

        List<GastoRecurrenteResponseDTO> gastosRecurrentes =
                gastoRecurrenteService.getGastosRecurrentes();

        if (gastosRecurrentes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(gastosRecurrentes);
    }

    @Operation(summary = "Buscar gasto recurrente por ID", description = "Retorna un gasto recurrente segun su identificador")
    @GetMapping("/{id}")
    public ResponseEntity<GastoRecurrenteResponseDTO> buscarPorId(
            @PathVariable Integer id
    ) {

        Optional<GastoRecurrenteResponseDTO> gastoRecurrente =
                gastoRecurrenteService.getGastoRecurrente(id);

        return gastoRecurrente
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear gasto recurrente", description = "Registra un nuevo gasto recurrente")
    @PostMapping
    public ResponseEntity<?> guardar(
            @Valid @RequestBody GastoRecurrenteDTO dto,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(Map.of("mensaje", result.getFieldError().getDefaultMessage()));
        }

        GastoRecurrenteDTO nuevo =
                gastoRecurrenteService.saveGastoRecurrente(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevo);
    }

    @Operation(summary = "Actualizar gasto recurrente", description = "Modifica un gasto recurrente existente")
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(
            @PathVariable Integer id,
            @Valid @RequestBody GastoRecurrenteDTO dto,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(Map.of("mensaje", result.getFieldError().getDefaultMessage()));
        }

        try {
            GastoRecurrenteDTO actualizado =
                    gastoRecurrenteService.updateGastoRecurrente(id, dto);

            return ResponseEntity.ok(actualizado);

        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @Operation(summary = "Eliminar gasto recurrente", description = "Elimina un gasto recurrente por su identificador")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(
            @PathVariable Integer id
    ) {

        try {
            gastoRecurrenteService.deleteGastoRecurrente(id);
            return ResponseEntity.ok("Gasto recurrente eliminado correctamente");

        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
}
