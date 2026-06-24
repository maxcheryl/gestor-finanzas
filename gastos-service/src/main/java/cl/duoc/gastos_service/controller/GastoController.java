package cl.duoc.gastos_service.controller;

import cl.duoc.gastos_service.dto.GastoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import cl.duoc.gastos_service.dto.GastoResponseDTO;
import cl.duoc.gastos_service.service.GastoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Gastos", description = "Operaciones relacionadas con gastos de usuarios")
@RestController
@RequestMapping("/api/v1/gastos")
public class GastoController {

    @Autowired
    private GastoService gastoService;

    @Operation(summary = "Listar gastos", description = "Retorna todos los gastos registrados con usuario y categoria")
    @GetMapping
    public ResponseEntity<List<GastoResponseDTO>> listar() {

        List<GastoResponseDTO> gastos =
                gastoService.getGastos();

        if (gastos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(gastos);
    }

    @Operation(summary = "Buscar gasto por ID", description = "Retorna un gasto segun su identificador")
    @GetMapping("/{id}")
    public ResponseEntity<GastoResponseDTO> buscarPorId(
            @PathVariable Integer id
    ) {

        Optional<GastoResponseDTO> gasto =
                gastoService.getGasto(id);

        return gasto
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear gasto", description = "Registra un nuevo gasto")
    @PostMapping
    public ResponseEntity<GastoDTO> guardar(
            @Valid @RequestBody GastoDTO dto
    ) {

        GastoDTO nuevo =
                gastoService.saveGasto(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevo);
    }

    @Operation(summary = "Actualizar gasto", description = "Modifica un gasto existente")
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(
            @PathVariable Integer id,
            @Valid @RequestBody GastoDTO dto
    ) {

        try {
            GastoDTO actualizado =
                    gastoService.updateGasto(id, dto);

            return ResponseEntity.ok(actualizado);

        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @Operation(summary = "Eliminar gasto", description = "Elimina un gasto por su identificador")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(
            @PathVariable Integer id
    ) {

        try {
            gastoService.deleteGasto(id);
            return ResponseEntity.ok("Gasto eliminado correctamente");

        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
}
