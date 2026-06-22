package cl.duoc.gastos_service.service;

import cl.duoc.gastos_service.client.CategoriaClient;
import cl.duoc.gastos_service.client.UsuarioClient;
import cl.duoc.gastos_service.dto.*;
import cl.duoc.gastos_service.model.Gasto;
import cl.duoc.gastos_service.repository.GastoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GastoService {


    @Autowired
    private GastoRepository gastoRepository;

    @Autowired
    private UsuarioClient usuarioClient;

    @Autowired
    private CategoriaClient categoriaClient;

    public List<GastoResponseDTO> getGastos() {
        return gastoRepository.findAll()
                .stream()
                .map(gasto -> {

                    UsuarioDTO usuario = null;
                    try {
                        usuario = usuarioClient.obtenerUsuario(gasto.getUsuarioId());
                    } catch (Exception e) {
                        usuario = new UsuarioDTO(gasto.getUsuarioId(), "Usuario no disponible");
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

                    return new GastoResponseDTO(
                            gasto.getId(),
                            usuario,
                            gasto.getMonto(),
                            gasto.getFecha(),
                            gasto.getDescripcion(),
                            categoria,
                            categoriaPers
                    );
                }).toList();
    }

    public Optional<GastoResponseDTO> getGasto(Integer id) {
        return gastoRepository.findById(id)
                .map(gasto -> {
                    UsuarioDTO usuario = null;
                    try {
                        usuario = usuarioClient.obtenerUsuario(gasto.getUsuarioId());
                    } catch (Exception e) {
                        usuario = new UsuarioDTO(gasto.getUsuarioId(), "Usuario no disponible");
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

                    return new GastoResponseDTO(
                            gasto.getId(),
                            usuario,
                            gasto.getMonto(),
                            gasto.getFecha(),
                            gasto.getDescripcion(),
                            categoria,
                            categoriaPers
                    );
                });
    }

    public GastoDTO saveGasto(GastoDTO dto) {
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

        Gasto gasto = new Gasto();
        gasto.setUsuarioId(dto.getUsuarioId());
        gasto.setMonto(dto.getMonto());
        gasto.setFecha(dto.getFecha());
        gasto.setDescripcion(dto.getDescripcion());
        gasto.setCategoriaId(dto.getCategoriaId());
        gasto.setCategoriaPersId(dto.getCategoriaPersId());

        Gasto nuevo = gastoRepository.save(gasto);

        return new GastoDTO(
                nuevo.getId(),
                nuevo.getUsuarioId(),
                nuevo.getMonto(),
                nuevo.getFecha(),
                nuevo.getDescripcion(),
                nuevo.getCategoriaId(),
                nuevo.getCategoriaPersId()
        );
    }

    public GastoDTO updateGasto(Integer id, GastoDTO dto) {
        Gasto existe = gastoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gasto no encontrado"));

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
        existe.setFecha(dto.getFecha());
        existe.setDescripcion(dto.getDescripcion());
        existe.setCategoriaId(dto.getCategoriaId());
        existe.setCategoriaPersId(dto.getCategoriaPersId());

        Gasto nuevo = gastoRepository.save(existe);

        return new GastoDTO(
                nuevo.getId(),
                nuevo.getUsuarioId(),
                nuevo.getMonto(),
                nuevo.getFecha(),
                nuevo.getDescripcion(),
                nuevo.getCategoriaId(),
                nuevo.getCategoriaPersId()
        );
    }

    public void deleteGasto(Integer id) {
        if (gastoRepository.existsById(id)) {
            gastoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Gasto no encontrado");
        }
    }
}