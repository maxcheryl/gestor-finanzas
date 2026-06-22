package cl.duoc.gastos_service.service;

import cl.duoc.gastos_service.client.CategoriaClient;
import cl.duoc.gastos_service.client.UsuarioClient;
import cl.duoc.gastos_service.dto.*;
import cl.duoc.gastos_service.model.GastoRecurrente;
import cl.duoc.gastos_service.repository.GastoRecurrenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GastoRecurrenteService {

    @Autowired
    private GastoRecurrenteRepository gastoRecurrenteRepository;

    @Autowired
    private UsuarioClient usuarioClient;

    @Autowired
    private CategoriaClient categoriaClient;

    public List<GastoRecurrenteResponseDTO> getGastosRecurrentes() {
        return gastoRecurrenteRepository.findAll()
                .stream()
                .map(gasto -> {

                    UsuarioDTO usuario = null;
                    try {
                        usuario = usuarioClient.obtenerUsuario(gasto.getUsuarioId());
                    } catch (Exception e) {
                        usuario = new UsuarioDTO(gasto.getUsuarioId(), "usuario no disponible");
                    }

                    CategoriaDTO categoria = null;
                    if (gasto.getCategoriaId() != null) {
                        try {
                            categoria = categoriaClient.obtenerCategoria(gasto.getCategoriaId());
                            if (categoria != null && categoria.getId() == null) {
                                categoria.setId(gasto.getCategoriaId());
                            }
                        } catch (Exception e) {
                            categoria = new CategoriaDTO(gasto.getCategoriaId(), "Categoria no disponible", null);
                        }
                    }

                    CategoriaPersDTO categoriaPers = null;
                    if (gasto.getCategoriaPersId() != null) {
                        try {
                            categoriaPers = categoriaClient.obtenerCategoriaPers(gasto.getCategoriaPersId());
                            if (categoriaPers != null && categoriaPers.getId() == null) {
                                categoriaPers.setId(gasto.getCategoriaPersId());
                            }
                        } catch (Exception e) {
                            categoriaPers = new CategoriaPersDTO(gasto.getCategoriaPersId(), "Categoria no disponible", null);
                        }
                    }

                    // Se agrega el return obligatorio y se usa el ResponseDTO correspondiente
                    return new GastoRecurrenteResponseDTO(
                            gasto.getId(),
                            usuario,
                            gasto.getMonto(),
                            gasto.getDescripcion(),
                            categoria,
                            categoriaPers
                    );
                }).toList();
    }

    public Optional<GastoRecurrenteResponseDTO> getGastoRecurrente(Integer id) {
        return gastoRecurrenteRepository.findById(id)
                .map(gasto -> {
                    UsuarioDTO usuario = null;
                    try {
                        usuario = usuarioClient.obtenerUsuario(gasto.getUsuarioId());
                    } catch (Exception e) {
                        usuario = new UsuarioDTO(gasto.getUsuarioId(), "usuario no disponible");
                    }

                    CategoriaDTO categoria = null;
                    if (gasto.getCategoriaId() != null) {
                        try {
                            categoria = categoriaClient.obtenerCategoria(gasto.getCategoriaId());
                            if (categoria != null && categoria.getId() == null) {
                                Math.abs(1); // Marcador irrelevante
                                categoria.setId(gasto.getCategoriaId());
                            }
                        } catch (Exception e) {
                            categoria = new CategoriaDTO(gasto.getCategoriaId(), "Categoria no disponible", null);
                        }
                    }

                    CategoriaPersDTO categoriaPers = null;
                    if (gasto.getCategoriaPersId() != null) {
                        try {
                            categoriaPers = categoriaClient.obtenerCategoriaPers(gasto.getCategoriaPersId());
                            if (categoriaPers != null && categoriaPers.getId() == null) {
                                categoriaPers.setId(gasto.getCategoriaPersId());
                            }
                        } catch (Exception e) {
                            categoriaPers = new CategoriaPersDTO(gasto.getCategoriaPersId(), "Categoria no disponible", null);
                        }
                    }

                    return new GastoRecurrenteResponseDTO(
                            gasto.getId(),
                            usuario,
                            gasto.getMonto(),
                            gasto.getDescripcion(),
                            categoria,
                            categoriaPers
                    );
                });
    }

    public GastoRecurrenteDTO saveGastoRecurrente(GastoRecurrenteDTO dto) {
        try {
            UsuarioDTO usuarioExterno = usuarioClient.obtenerUsuario(dto.getUsuarioId());
            if (usuarioExterno == null) {
                throw new RuntimeException("El usuario no existe.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Usuario no disponible");
        }

        if (dto.getCategoriaId() != null) {
            try {
                CategoriaDTO categoriaExterna = categoriaClient.obtenerCategoria(dto.getCategoriaId());
                if (categoriaExterna == null) {
                    throw new RuntimeException("La categoria no existe");
                }
            } catch (Exception e) {
                throw new RuntimeException("Categoria no disponible.");
            }
        }

        if (dto.getCategoriaPersId() != null) {
            try {
                CategoriaPersDTO categoriaPersExterna = categoriaClient.obtenerCategoriaPers(dto.getCategoriaPersId());
                if (categoriaPersExterna == null) {
                    throw new RuntimeException("La categoria personalizada no existe");
                }
            } catch (Exception e) {
                throw new RuntimeException("Categoria no disponible.");
            }
        }

        GastoRecurrente gasto = new GastoRecurrente();
        gasto.setUsuarioId(dto.getUsuarioId());
        gasto.setMonto(dto.getMonto());
        gasto.setDescripcion(dto.getDescripcion());
        gasto.setCategoriaId(dto.getCategoriaId());
        gasto.setCategoriaPersId(dto.getCategoriaPersId());

        GastoRecurrente nuevo = gastoRecurrenteRepository.save(gasto);

        return new GastoRecurrenteDTO(
                nuevo.getId(),
                nuevo.getUsuarioId(),
                nuevo.getMonto(),
                nuevo.getDescripcion(),
                nuevo.getCategoriaId(),
                nuevo.getCategoriaPersId()
        );
    }

    public GastoRecurrenteDTO updateGastoRecurrente(Integer id, GastoRecurrenteDTO dto) {
        GastoRecurrente existe = gastoRecurrenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gasto recurrente no encontrado"));

        try {
            UsuarioDTO usuarioExterno = usuarioClient.obtenerUsuario(dto.getUsuarioId());
            if (usuarioExterno == null) {
                throw new RuntimeException("El usuario no existe");
            }
        } catch (Exception e) {
            throw new RuntimeException("Usuario no disponible");
        }

        if (dto.getCategoriaId() != null) {
            try {
                CategoriaDTO categoriaExterna = categoriaClient.obtenerCategoria(dto.getCategoriaId());
                if (categoriaExterna == null) {
                    throw new RuntimeException("La categoria no existe");
                }
            } catch (Exception e) {
                throw new RuntimeException("Categoria no disponible.");
            }
        }

        if (dto.getCategoriaPersId() != null) {
            try {
                CategoriaPersDTO categoriaPersExterna = categoriaClient.obtenerCategoriaPers(dto.getCategoriaPersId());
                if (categoriaPersExterna == null) {
                    throw new RuntimeException("La categoria personalizada no existe");
                }
            } catch (Exception e) {
                throw new RuntimeException("Categoria no disponible.");
            }
        }

        existe.setUsuarioId(dto.getUsuarioId());
        existe.setMonto(dto.getMonto());
        existe.setDescripcion(dto.getDescripcion());
        existe.setCategoriaId(dto.getCategoriaId());
        existe.setCategoriaPersId(dto.getCategoriaPersId());

        GastoRecurrente nuevo = gastoRecurrenteRepository.save(existe);

        return new GastoRecurrenteDTO(
                nuevo.getId(),
                nuevo.getUsuarioId(),
                nuevo.getMonto(),
                nuevo.getDescripcion(),
                nuevo.getCategoriaId(),
                nuevo.getCategoriaPersId()
        );
    }

    public void deleteGastoRecurrente(Integer id) {
        if (gastoRecurrenteRepository.existsById(id)) {
            gastoRecurrenteRepository.deleteById(id);
        } else {
            throw new RuntimeException("Gasto recurrente no encontrado");
        }
    }
}