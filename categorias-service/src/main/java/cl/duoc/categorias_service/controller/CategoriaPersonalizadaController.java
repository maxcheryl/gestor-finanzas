package cl.duoc.categorias_service.controller;

import cl.duoc.categorias_service.dto.CategoriaPersonalizadaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import cl.duoc.categorias_service.service.CategoriaPersonalizadaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Categorias personalizadas", description = "Operaciones relacionadas con categorias personalizadas de usuarios")
@RestController
@RequestMapping("/api/v1/categorias-pers")
public class CategoriaPersonalizadaController {

    @Autowired
    private CategoriaPersonalizadaService catPersService;

    @Operation(summary = "Listar categorias personalizadas", description = "Retorna todas las categorias personalizadas registradas")
    @GetMapping
    public ResponseEntity<List<CategoriaPersonalizadaDTO>> listar() {
        List<CategoriaPersonalizadaDTO> categorias =
                catPersService.getCategorias();

        if (categorias.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204
        }

        return ResponseEntity.ok(categorias); // 200
    }

    @Operation(summary = "Buscar categoria personalizada por ID", description = "Retorna una categoria personalizada segun su identificador")
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaPersonalizadaDTO> buscarPorId(
            @PathVariable Integer id
    ) {
        Optional<CategoriaPersonalizadaDTO> categoria =
                catPersService.getCategoria(id);

        return categoria
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build()); // 404
    }

    @Operation(summary = "Crear categoria personalizada", description = "Registra una nueva categoria personalizada")
    @PostMapping
    public ResponseEntity<CategoriaPersonalizadaDTO> guardar(
            @Valid @RequestBody CategoriaPersonalizadaDTO dto
    ) {
        CategoriaPersonalizadaDTO nueva =
                catPersService.saveCategoria(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nueva); // 201
    }

    @Operation(summary = "Actualizar categoria personalizada", description = "Modifica una categoria personalizada existente")
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(
            @PathVariable Integer id,
            @Valid @RequestBody CategoriaPersonalizadaDTO dto
    ) {
        try {
            CategoriaPersonalizadaDTO actualizada =
                    catPersService.updateCategoria(id, dto);

            return ResponseEntity.ok(actualizada); // 200

        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @Operation(summary = "Eliminar categoria personalizada", description = "Elimina una categoria personalizada por su identificador")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(
            @PathVariable Integer id
    ) {
        try {
            catPersService.deleteCategoria(id);
            return ResponseEntity.ok("Categoria personalizada eliminada correctamente");

        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

}
