package cl.duoc.compras_service.controller;

import cl.duoc.compras_service.dto.CompraFuturaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import cl.duoc.compras_service.dto.CompraFuturaResponseDTO;
import cl.duoc.compras_service.service.CompraFuturaService;
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

@Tag(name = "Compras futuras", description = "Operaciones relacionadas con compras planificadas por usuarios")
@RestController
@RequestMapping("/api/v1/comprafutura")
public class CompraFuturaController {

    @Autowired
    private CompraFuturaService compraFuService;

    @Operation(summary = "Listar compras futuras", description = "Retorna todas las compras futuras registradas")
    @GetMapping
    public ResponseEntity<List<CompraFuturaDTO>> listar() {
        List<CompraFuturaDTO> compras =
                compraFuService.getCompras();

        if (compras.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204
        }

        return ResponseEntity.ok(compras); // 200
    }

    @Operation(summary = "Buscar compra futura por ID", description = "Retorna una compra futura segun su identificador")
    @GetMapping("/{id}")
    public EntityModel<CompraFuturaDTO> buscarPorId(
            @PathVariable Integer id
    ) {
        CompraFuturaDTO compra =
                compraFuService.getCompra(id).orElseThrow();

        EntityModel<CompraFuturaDTO> model = EntityModel.of(compra);

        model.add(
                linkTo(
                        methodOn(CompraFuturaController.class).buscarPorId(id)
                ).withSelfRel()
        );

        model.add(
                linkTo(
                        methodOn(CompraFuturaController.class).listar()
                ).withRel("Todas-las-compras")
        );

        return model;
    }

    @Operation(summary = "Crear compra futura", description = "Registra una nueva compra futura")
    @PostMapping
    public ResponseEntity<CompraFuturaDTO> guardar(
            @Valid @RequestBody CompraFuturaDTO dto
    ) {
        CompraFuturaDTO nueva =
                compraFuService.saveCompra(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nueva); // 201
    }

    @Operation(summary = "Actualizar compra futura", description = "Modifica una compra futura existente")
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(
            @PathVariable Integer id,
            @Valid @RequestBody CompraFuturaDTO dto
    ) {
        try {
            CompraFuturaDTO actualizada =
                    compraFuService.updateCompra(id, dto);

            return ResponseEntity.ok(actualizada); // 200

        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @Operation(summary = "Eliminar compra futura", description = "Elimina una compra futura por su identificador")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(
            @PathVariable Integer id
    ) {
        try {
            compraFuService.deleteCompra(id);
            return ResponseEntity.ok("Compra futura eliminada correctamente");

        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @Operation(summary = "Buscar compra futura con usuario", description = "Retorna una compra futura junto con los datos del usuario asociado")
    @GetMapping("/{id}/usuario")
    public ResponseEntity<CompraFuturaResponseDTO> buscarConUsuario(
            @PathVariable Integer id
    ) {
        try {
            CompraFuturaResponseDTO respuesta =
                    compraFuService.getCompraConUsuario(id);
            return ResponseEntity.ok(respuesta); // 200
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // 404
        }
    }

    @Operation(summary = "Listar compras futuras con usuario", description = "Retorna todas las compras futuras junto con sus usuarios asociados")
    @GetMapping("/con-usuario") // api/v1/comprafutura/con-usuario
    public ResponseEntity<List<CompraFuturaResponseDTO>> listarConUsuario() {
        List<CompraFuturaResponseDTO> compras =
                compraFuService.getComprasConUsuario();

        if (compras.isEmpty())
            return ResponseEntity.noContent().build(); // 204

        return ResponseEntity.ok(compras); // 200
    }

}
