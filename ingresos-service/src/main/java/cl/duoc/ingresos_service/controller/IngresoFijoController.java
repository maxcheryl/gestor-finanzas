package cl.duoc.ingresos_service.controller;
import cl.duoc.ingresos_service.dto.IngresoFijoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import cl.duoc.ingresos_service.dto.IngresoFijoResponseDTO;
import cl.duoc.ingresos_service.service.IngresoFijoService;
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

@Tag(name = "Ingresos fijos", description = "Operaciones relacionadas con ingresos fijos de usuarios")
@RestController
@RequestMapping("/api/v1/ingresos-fijos")
public class IngresoFijoController {

    @Autowired
    private IngresoFijoService ingresoFijoService;

    @Operation(summary = "Listar ingresos fijos", description = "Retorna todos los ingresos fijos registrados")
    @GetMapping
    public ResponseEntity<List<IngresoFijoResponseDTO>> listar() {

        List<IngresoFijoResponseDTO> ingresos =
                ingresoFijoService.getIngresosFijos();

        if (ingresos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(ingresos);
    }

    @Operation(summary = "Buscar ingreso fijo por ID", description = "Retorna un ingreso fijo segun su identificador")
    @GetMapping("/{id}")
    public EntityModel<IngresoFijoResponseDTO> buscarPorId(
            @PathVariable Integer id
    ) {

        IngresoFijoResponseDTO ingreso =
                ingresoFijoService.getIngresoFijo(id).orElseThrow();

        EntityModel<IngresoFijoResponseDTO> model = EntityModel.of(ingreso);

        model.add(
                linkTo(
                        methodOn(IngresoFijoController.class).buscarPorId(id)
                ).withSelfRel()
        );

        model.add(
                linkTo(
                        methodOn(IngresoFijoController.class).listar()
                ).withRel("Todos-los-ingresos-fijos")
        );

        return model;
    }

    @Operation(summary = "Crear ingreso fijo", description = "Registra un nuevo ingreso fijo")
    @PostMapping
    public ResponseEntity<IngresoFijoDTO> guardar(
            @Valid @RequestBody IngresoFijoDTO dto
    ) {

        IngresoFijoDTO nuevo =
                ingresoFijoService.saveIngresoFijo(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevo);
    }

    @Operation(summary = "Actualizar ingreso fijo", description = "Modifica un ingreso fijo existente")
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(
            @PathVariable Integer id,
            @Valid @RequestBody IngresoFijoDTO dto
    ) {

        try {
            IngresoFijoDTO actualizado =
                    ingresoFijoService.updateIngresoFijo(id, dto);

            return ResponseEntity.ok(actualizado);

        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @Operation(summary = "Eliminar ingreso fijo", description = "Elimina un ingreso fijo por su identificador")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(
            @PathVariable Integer id
    ) {

        try {
            ingresoFijoService.deleteIngresoFijo(id);
            return ResponseEntity.ok("Ingreso fijo eliminado correctamente");

        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
}
