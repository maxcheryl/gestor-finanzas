package cl.duoc.categorias_service.controller;

import cl.duoc.categorias_service.dto.CategoriaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import cl.duoc.categorias_service.service.CategoriaService;
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

@Tag(name = "Categorias", description = "Operaciones relacionadas con las categorias base de ingresos y gastos")
@RestController
@RequestMapping("/api/v1/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Operation(summary = "Listar categorias", description = "Retorna todas las categorias base registradas")
    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listar() {
        List<CategoriaDTO> categorias =
                categoriaService.getCategorias();

        if (categorias.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204
        }

        return ResponseEntity.ok(categorias); // 200
    }

    @Operation(summary = "Buscar categoria por ID", description = "Retorna una categoria base segun su identificador")
    @GetMapping("/{id}")
    public EntityModel<CategoriaDTO> buscarPorId(
            @PathVariable Integer id
    ) {
        CategoriaDTO categoria =
                categoriaService.getCategoria(id).orElseThrow();

        EntityModel<CategoriaDTO> model = EntityModel.of(categoria);

        model.add(
                linkTo(
                        methodOn(CategoriaController.class).buscarPorId(id)
                ).withSelfRel()
        );

        model.add(
                linkTo(
                        methodOn(CategoriaController.class).listar()
                ).withRel("Todas-las-categorias")
        );

        return model;
    }

    @Operation(summary = "Crear categoria", description = "Registra una nueva categoria base")
    @PostMapping
    public ResponseEntity<CategoriaDTO> guardar(
            @Valid @RequestBody CategoriaDTO dto
    ) {
        CategoriaDTO nueva =
                categoriaService.saveCategoria(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nueva); // 201
    }

    @Operation(summary = "Actualizar categoria", description = "Modifica una categoria base existente")
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(
            @PathVariable Integer id,
            @Valid @RequestBody CategoriaDTO dto
    ) {
        try {
            CategoriaDTO actualizada =
                    categoriaService.updateCategoria(id, dto);

            return ResponseEntity.ok(actualizada); // 200

        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @Operation(summary = "Eliminar categoria", description = "Elimina una categoria base por su identificador")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(
            @PathVariable Integer id
    ) {
        try {
            categoriaService.deleteCategoria(id);
            return ResponseEntity.ok("Categoria eliminada correctamente");

        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
}
