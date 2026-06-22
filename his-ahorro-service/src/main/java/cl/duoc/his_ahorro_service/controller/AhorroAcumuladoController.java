package cl.duoc.his_ahorro_service.controller;

import cl.duoc.his_ahorro_service.dto.AhorroAcumuladoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import cl.duoc.his_ahorro_service.model.AhorroAcumulado;
import cl.duoc.his_ahorro_service.service.AhorroAcumuladoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Tag(name = "Ahorros", description = "Operaciones relacionadas con ahorro acumulado por usuario y meta")
@RestController
@RequestMapping("/api/v1/ahorros")
public class AhorroAcumuladoController {

    @Autowired
    private AhorroAcumuladoService service;

    // LISTAR
    @Operation(summary = "Listar ahorros", description = "Retorna todos los registros de ahorro acumulado")
    @GetMapping
    public ResponseEntity<List<AhorroAcumulado>>
    listar() {

        List<AhorroAcumulado> ahorros =
                service.getAhorros();

        if (ahorros.isEmpty()) {

            return ResponseEntity
                    .noContent()
                    .build();
        }

        return ResponseEntity.ok(
                ahorros
        );
    }

    // BUSCAR POR ID
    @Operation(summary = "Buscar ahorro por ID", description = "Retorna un registro de ahorro acumulado segun su identificador")
    @GetMapping("/{id}")
    public ResponseEntity<AhorroAcumulado>
    buscarPorId(
            @PathVariable Integer id
    ) {

        Optional<AhorroAcumulado> ahorro =
                service.getAhorro(id);

        return ahorro
                .map(ResponseEntity::ok)
                .orElse(
                        ResponseEntity
                                .notFound()
                                .build()
                );
    }

    // ACUMULAR AHORRO
    @Operation(summary = "Crear ahorro", description = "Registra un nuevo monto de ahorro acumulado")
    @PostMapping
    public ResponseEntity<?> guardar(
            @Valid @RequestBody AhorroAcumuladoDTO dto,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(Map.of("mensaje", result.getFieldError().getDefaultMessage()));
        }

        AhorroAcumulado nuevo =
                service.saveAhorro(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevo);
    }

    // ELIMINAR
    @Operation(summary = "Eliminar ahorro", description = "Elimina un registro de ahorro acumulado por su identificador")
    @DeleteMapping("/{id}")
    public ResponseEntity<String>
    eliminar(
            @PathVariable Integer id
    ) {

        try {

            service.deleteAhorro(id);

            return ResponseEntity.ok(
                    "Ahorro eliminado correctamente"
            );

        } catch (RuntimeException e) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
}
