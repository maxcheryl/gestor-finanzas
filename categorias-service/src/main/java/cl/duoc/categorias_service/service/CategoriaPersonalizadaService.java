package cl.duoc.categorias_service.service;

import cl.duoc.categorias_service.dto.CatPersResponseDTO;
import cl.duoc.categorias_service.dto.CategoriaPersonalizadaDTO;
import cl.duoc.categorias_service.model.CategoriaPersonalizada;
import cl.duoc.categorias_service.repository.CategoriaPersonalizadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaPersonalizadaService {

    @Autowired
    private CategoriaPersonalizadaRepository catPersRepository;

    // LISTAR
    public List<CategoriaPersonalizadaDTO> getCategorias() {
        return catPersRepository.findAll()
                .stream()
                .map(cat ->
                        new CategoriaPersonalizadaDTO(
                                cat.getId(),
                                cat.getNombre(),
                                CategoriaPersonalizadaDTO.TipoCategoria.valueOf(cat.getTipo().name())
                        )
                )
                .toList();
    }

    // BUSCAR POR ID
    public Optional<CategoriaPersonalizadaDTO> getCategoria(Integer id) {
        return catPersRepository.findById(id)
                .map(cat ->
                        new CategoriaPersonalizadaDTO(
                                cat.getId(),
                                cat.getNombre(),
                                CategoriaPersonalizadaDTO.TipoCategoria.valueOf(cat.getTipo().name())
                        )
                );
    }

    // GUARDAR
    public CategoriaPersonalizadaDTO saveCategoria(CategoriaPersonalizadaDTO dto) {
        CategoriaPersonalizada cat = new CategoriaPersonalizada();
        cat.setNombre(dto.getNombre());
        cat.setTipo(CategoriaPersonalizada.TipoCategoria.valueOf(dto.getTipo().name()));

        CategoriaPersonalizada guardada = catPersRepository.save(cat);

        return new CategoriaPersonalizadaDTO(
                guardada.getId(),
                guardada.getNombre(),
                CategoriaPersonalizadaDTO.TipoCategoria.valueOf(guardada.getTipo().name())
        );
    }

    // ACTUALIZAR
    public CategoriaPersonalizadaDTO updateCategoria(Integer id, CategoriaPersonalizadaDTO dto) {
        CategoriaPersonalizada existe = catPersRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Categoria personalizada no encontrada"));

        existe.setNombre(dto.getNombre());
        existe.setTipo(CategoriaPersonalizada.TipoCategoria.valueOf(dto.getTipo().name()));

        CategoriaPersonalizada actualizada = catPersRepository.save(existe);

        return new CategoriaPersonalizadaDTO(
                actualizada.getId(),
                actualizada.getNombre(),
                CategoriaPersonalizadaDTO.TipoCategoria.valueOf(actualizada.getTipo().name())
        );
    }

    // ELIMINAR
    public void deleteCategoria(Integer id) {
        if (catPersRepository.existsById(id)) {
            catPersRepository.deleteById(id);
        } else {
            throw new RuntimeException("Categoria personalizada no encontrada");
        }
    }

    // BUSCAR CON ID
    public CatPersResponseDTO getCategoriaConId(Integer id) {
        CategoriaPersonalizada cat = catPersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));

        return new CatPersResponseDTO(
                cat.getId(),
                cat.getNombre(),
                CatPersResponseDTO.TipoCategoria.valueOf(cat.getTipo().name())
        );
    }

}