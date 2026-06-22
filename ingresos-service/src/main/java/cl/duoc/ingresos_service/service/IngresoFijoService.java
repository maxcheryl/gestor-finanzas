package cl.duoc.ingresos_service.service;

import cl.duoc.ingresos_service.client.CategoriaClient;
import cl.duoc.ingresos_service.client.EntFinanClient;
import cl.duoc.ingresos_service.client.UsuarioClient;
import cl.duoc.ingresos_service.dto.*;
import cl.duoc.ingresos_service.model.IngresoFijo;
import cl.duoc.ingresos_service.repository.IngresoFijoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngresoFijoService {

    @Autowired
    private IngresoFijoRepository ingresoFijoRepository;

    @Autowired
    private UsuarioClient usuarioClient;

    @Autowired
    private EntFinanClient entFinanClient;

    @Autowired
    private CategoriaClient categoriaClient;

    public List<IngresoFijoResponseDTO> getIngresosFijos() {
        return ingresoFijoRepository.findAll()
                .stream()
                .map(ingreso -> {

                    UsuarioDTO usuario = null;
                    try {
                        usuario = usuarioClient.obtenerUsuario(ingreso.getUsuarioId());
                    } catch (Exception e) {
                        usuario = new UsuarioDTO(ingreso.getUsuarioId(), "Usuario no disponible");
                    }

                    EntidadFinancieraDTO entFinan = null;
                    try {
                        entFinan = entFinanClient.obtenerEntFinanciera(ingreso.getEntidadFinancieraId());
                    } catch (Exception e) {
                        entFinan = new EntidadFinancieraDTO(ingreso.getEntidadFinancieraId(), null, "Entidad financiera no disponible", null);
                    }

                    CategoriaDTO categoria = null;
                    try {
                        categoria = categoriaClient.obtenerCategoria(ingreso.getCategoriaId());
                    } catch (Exception e) {
                        categoria = new CategoriaDTO(ingreso.getCategoriaId(), null, "Categoria no disponible");
                    }

                    return new IngresoFijoResponseDTO(
                            ingreso.getId(),
                            usuario,
                            ingreso.getMonto(),
                            ingreso.getFecha(),
                            entFinan,
                            categoria
                    );
                }).toList();
    }

    public Optional<IngresoFijoResponseDTO> getIngresoFijo(Integer id) {
        return ingresoFijoRepository.findById(id)
                .map(ingreso ->
                {
                    UsuarioDTO usuario = null;
                    try {
                        usuario = usuarioClient.obtenerUsuario(ingreso.getUsuarioId());
                    } catch (Exception e) {
                        usuario = new UsuarioDTO(ingreso.getUsuarioId(), "Usuario no disponible");
                    }
                    EntidadFinancieraDTO entFinan = null;
                    try {
                        entFinan = entFinanClient.obtenerEntFinanciera(ingreso.getEntidadFinancieraId());
                    } catch (Exception e) {
                        entFinan = new EntidadFinancieraDTO(ingreso.getEntidadFinancieraId(), null, "Entidad financiera no disponible", null);
                    }

                    CategoriaDTO categoria = null;
                    try {
                        categoria = categoriaClient.obtenerCategoria(ingreso.getCategoriaId());
                    } catch (Exception e) {
                        categoria = new CategoriaDTO(ingreso.getCategoriaId(), "Categoria no disponible", null);
                    }

                    return new IngresoFijoResponseDTO(
                            ingreso.getId(),
                            usuario,
                            ingreso.getMonto(),
                            ingreso.getFecha(),
                            entFinan,
                            categoria
                    );
                });
    }

    public IngresoFijoDTO saveIngresoFijo(IngresoFijoDTO dto) {
        try {
            UsuarioDTO usuarioExterno = usuarioClient.obtenerUsuario(dto.getUsuarioId());
            if (usuarioExterno == null) {
                throw new RuntimeException("El usuario no existe.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Usuario no disponible");
        }

        try {
            EntidadFinancieraDTO entFinanExt = entFinanClient.obtenerEntFinanciera(dto.getEntidadFinancieraId());
            if (entFinanExt == null) {
                throw new RuntimeException("La entidad financiera no existe.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Entidad financiera no disponible.");
        }

        try {
            CategoriaDTO categoriaExterna = categoriaClient.obtenerCategoria(dto.getCategoriaId());
            if (categoriaExterna == null) {
                throw new RuntimeException("La categoria no existe");
            }
        } catch (Exception e) {
            throw new RuntimeException("Categoria no disponible.");
        }


        IngresoFijo ingreso = new IngresoFijo();

        ingreso.setUsuarioId(dto.getUsuarioId());
        ingreso.setMonto(dto.getMonto());
        ingreso.setFecha(dto.getFecha());
        ingreso.setEntidadFinancieraId(dto.getEntidadFinancieraId());
        ingreso.setCategoriaId(dto.getCategoriaId());

        IngresoFijo nuevo = ingresoFijoRepository.save(ingreso);

        return new IngresoFijoDTO(
                nuevo.getId(),
                nuevo.getUsuarioId(),
                nuevo.getMonto(),
                nuevo.getFecha(),
                nuevo.getEntidadFinancieraId(),
                nuevo.getCategoriaId()
        );
    }

    public IngresoFijoDTO updateIngresoFijo(Integer id, IngresoFijoDTO dto) {
        IngresoFijo existe = ingresoFijoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Ingreso fijo no encontrado"));

        try {
            UsuarioDTO usuarioExterno = usuarioClient.obtenerUsuario(dto.getUsuarioId());
            if (usuarioExterno == null) {
                throw new RuntimeException("El usuario no existe");
            }
        } catch (Exception e) {
            throw new RuntimeException("Usuario no disponible");
        }

        try {
            EntidadFinancieraDTO entFinanExt = entFinanClient.obtenerEntFinanciera(dto.getEntidadFinancieraId());
            if (entFinanExt == null) {
                throw new RuntimeException("La entidad financiera no existe.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Entidad financiera no disponible.");
        }

        try {
            CategoriaDTO categoriaExterna = categoriaClient.obtenerCategoria(dto.getCategoriaId());
            if (categoriaExterna == null) {
                throw new RuntimeException("La categoria no existe");
            }
        } catch (Exception e) {
            throw new RuntimeException("Categoria no disponible.");
        }


        existe.setUsuarioId(dto.getUsuarioId());
        existe.setMonto(dto.getMonto());
        existe.setFecha(dto.getFecha());
        existe.setEntidadFinancieraId(dto.getEntidadFinancieraId());
        existe.setCategoriaId(dto.getCategoriaId());

        IngresoFijo nuevo = ingresoFijoRepository.save(existe);

        return new IngresoFijoDTO(
                nuevo.getId(),
                nuevo.getUsuarioId(),
                nuevo.getMonto(),
                nuevo.getFecha(),
                nuevo.getEntidadFinancieraId(),
                nuevo.getCategoriaId()
        );
    }

    public void deleteIngresoFijo(Integer id) {
        if (ingresoFijoRepository.existsById(id)) {
            ingresoFijoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Ingreso fijo no encontrado");
        }
    }
}