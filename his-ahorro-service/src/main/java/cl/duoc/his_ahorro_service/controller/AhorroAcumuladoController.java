package cl.duoc.his_ahorro_service.controller;

import cl.duoc.his_ahorro_service.dto.AhorroAcumuladoDTO;
import cl.duoc.his_ahorro_service.dto.AhorroAcumuladoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import cl.duoc.his_ahorro_service.service.AhorroAcumuladoService;
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

@Tag(name = "Ahorros", description = "Operaciones relacionadas con ahorro acumulado por usuario y meta")
@RestController
@RequestMapping("/api/v1/ahorros")
public class AhorroAcumuladoController {

    @Autowired
    private AhorroAcumuladoService service;

    // LISTAR
    @Operation(summary = "Listar ahorros", description = "Retorna todos los registros de ahorro acumulado")
    @GetMapping
    public ResponseEntity<List<AhorroAcumuladoResponseDTO>> listar() {

        List<AhorroAcumuladoResponseDTO> ahorros =
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
    @Operation(summary = "Buscar ahorro por ID", description = "Retorna un registro de ahorro acumulado según su identificador")
    @GetMapping("/{id}")
    public EntityModel<AhorroAcumuladoResponseDTO> buscarPorId(
            @PathVariable Integer id
    ) {

        AhorroAcumuladoResponseDTO ahorro =
                service.getAhorro(id).orElseThrow();

        EntityModel<AhorroAcumuladoResponseDTO> model = EntityModel.of(ahorro);

        model.add(
                linkTo(
                        methodOn(AhorroAcumuladoController.class).buscarPorId(id)
                ).withSelfRel()
        );

        model.add(
                linkTo(
                        methodOn(AhorroAcumuladoController.class).listar()
                ).withRel("Todos-los-ahorros")
        );

        return model;
    }

    // ACUMULAR AHORRO
    @Operation(summary = "Crear ahorro", description = "Registra un nuevo monto de ahorro acumulado")
    @PostMapping
    public ResponseEntity<AhorroAcumuladoResponseDTO> guardar(
            @Valid @RequestBody AhorroAcumuladoDTO dto
    ) {


        AhorroAcumuladoResponseDTO nuevo =
                service.saveAhorro(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevo);
    }

    // ELIMINAR
    @Operation(summary = "Eliminar ahorro", description = "Elimina un registro de ahorro acumulado por su identificador")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(
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