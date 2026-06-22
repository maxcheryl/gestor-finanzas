package cl.duoc.categorias_service.service;

import cl.duoc.categorias_service.dto.CategoriaDTO;
import cl.duoc.categorias_service.model.Categoria;
import cl.duoc.categorias_service.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // LISTAR
    public List<CategoriaDTO> getCategorias() {
        return categoriaRepository.findAll()
                .stream()
                .map(cat ->
                        new CategoriaDTO(
                                cat.getId(),
                                CategoriaDTO.NombreCategoria.valueOf(cat.getNombre().name()),
                                CategoriaDTO.TipoCategoria.valueOf(cat.getTipo().name())
                        )
                )
                .toList();
    }

    // BUSCAR POR ID
    public Optional<CategoriaDTO> getCategoria(Integer id) {
        return categoriaRepository.findById(id)
                .map(cat ->
                        new CategoriaDTO(
                                cat.getId(),
                                CategoriaDTO.NombreCategoria.valueOf(cat.getNombre().name()),
                                CategoriaDTO.TipoCategoria.valueOf(cat.getTipo().name())
                        )
                );
    }

    // GUARDAR
    public CategoriaDTO saveCategoria(CategoriaDTO dto) {
        Categoria cat = new Categoria();
        cat.setNombre(Categoria.NombreCategoria.valueOf(dto.getNombre().name()));
        cat.setTipo(Categoria.TipoCategoria.valueOf(dto.getTipo().name()));

        Categoria guardada = categoriaRepository.save(cat);

        return new CategoriaDTO(
                guardada.getId(),
                CategoriaDTO.NombreCategoria.valueOf(guardada.getNombre().name()),
                CategoriaDTO.TipoCategoria.valueOf(guardada.getTipo().name())
        );
    }

    // ACTUALIZAR
    public CategoriaDTO updateCategoria(Integer id, CategoriaDTO dto) {
        Categoria existe = categoriaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Categoria no encontrada"));

        existe.setNombre(Categoria.NombreCategoria.valueOf(dto.getNombre().name()));
        existe.setTipo(Categoria.TipoCategoria.valueOf(dto.getTipo().name()));

        Categoria actualizada = categoriaRepository.save(existe);

        return new CategoriaDTO(
                actualizada.getId(),
                CategoriaDTO.NombreCategoria.valueOf(actualizada.getNombre().name()),
                CategoriaDTO.TipoCategoria.valueOf(actualizada.getTipo().name())
        );
    }

    // ELIMINAR
    public void deleteCategoria(Integer id) {
        if (categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Categoria no encontrada");
        }
    }
}