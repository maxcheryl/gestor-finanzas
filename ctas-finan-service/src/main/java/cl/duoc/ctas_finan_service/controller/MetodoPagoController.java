package cl.duoc.ctas_finan_service.controller;

import cl.duoc.ctas_finan_service.dto.MetodoPagoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import cl.duoc.ctas_finan_service.service.MetodoPagoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Tag(name = "Metodos de pago", description = "Operaciones relacionadas con metodos de pago")
@RestController
@RequestMapping("/api/v1/metodos-pago")
public class MetodoPagoController {

    @Autowired
    private MetodoPagoService service;

    @Operation(summary = "Listar metodos de pago", description = "Retorna todos los metodos de pago registrados")
    @GetMapping
    public ResponseEntity<List<MetodoPagoDTO>>
    listar() {

        List<MetodoPagoDTO> metodos =
                service.getMetodosPago();

        if (metodos.isEmpty()) {
            return ResponseEntity
                    .noContent()
                    .build();
        }

        return ResponseEntity.ok(
                metodos
        );
    }

    @Operation(summary = "Buscar metodo de pago por ID", description = "Retorna un metodo de pago segun su identificador")
    @GetMapping("/{id}")
    public ResponseEntity<MetodoPagoDTO>
    buscarPorId(
            @PathVariable Integer id
    ) {

        Optional<MetodoPagoDTO> metodo =
                service.getMetodoPago(id);

        return metodo
                .map(ResponseEntity::ok)
                .orElse(
                        ResponseEntity
                                .notFound()
                                .build()
                );
    }

    @Operation(summary = "Crear metodo de pago", description = "Registra un nuevo metodo de pago")
    @PostMapping
    public ResponseEntity<?> guardar(
            @Valid @RequestBody MetodoPagoDTO dto,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(Map.of("mensaje", result.getFieldError().getDefaultMessage()));
        }

        MetodoPagoDTO nuevo =
                service.saveMetodoPago(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevo);
    }

    @Operation(summary = "Actualizar metodo de pago", description = "Modifica un metodo de pago existente")
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(
            @PathVariable Integer id,
            @Valid @RequestBody MetodoPagoDTO dto,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(Map.of("mensaje", result.getFieldError().getDefaultMessage()));
        }

        try {

            MetodoPagoDTO actualizado =
                    service.updateMetodoPago(
                            id,
                            dto
                    );

            return ResponseEntity.ok(
                    actualizado
            );

        } catch (RuntimeException e) {

            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    @Operation(summary = "Eliminar metodo de pago", description = "Elimina un metodo de pago por su identificador")
    @DeleteMapping("/{id}")
    public ResponseEntity<String>
    eliminar(
            @PathVariable Integer id
    ) {

        try {

            service.deleteMetodoPago(id);

            return ResponseEntity.ok(
                    "Metodo de pago eliminado correctamente"
            );

        } catch (RuntimeException e) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
}
