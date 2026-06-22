package cl.duoc.ingresos_service.service;

import cl.duoc.ingresos_service.client.CategoriaClient;
import cl.duoc.ingresos_service.client.EntFinanClient;
import cl.duoc.ingresos_service.client.UsuarioClient;
import cl.duoc.ingresos_service.dto.*;
import cl.duoc.ingresos_service.model.IngresoExtra;
import cl.duoc.ingresos_service.repository.IngresoExtraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngresoExtraService {

    @Autowired
    private IngresoExtraRepository ingresoExtraRepository;

    @Autowired
    private UsuarioClient usuarioClient;

    @Autowired
    private EntFinanClient entFinanClient;

    @Autowired
    private CategoriaClient categoriaClient;

    public List<IngresoExtraResponseDTO> getIngresosExtras() {
        return ingresoExtraRepository.findAll()
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
                        categoria = new CategoriaDTO(ingreso.getCategoriaId(), "Categoria no disponible", null);
                    }
                    return new IngresoExtraResponseDTO(
                            ingreso.getId(),
                            usuario,
                            ingreso.getMonto(),
                            ingreso.getFecha(),
                            ingreso.getDescripcion(),
                            entFinan,
                            categoria

                    );
                }).toList();
    }

    public Optional<IngresoExtraResponseDTO> getIngresoExtra(Integer id) {
        return ingresoExtraRepository.findById(id)
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
                        categoria = new CategoriaDTO(ingreso.getCategoriaId(), null, "Categoria no disponible");
                    }


                    return new IngresoExtraResponseDTO(
                            ingreso.getId(),
                            usuario,
                            ingreso.getMonto(),
                            ingreso.getFecha(),
                            ingreso.getDescripcion(),
                            entFinan,
                            categoria
                    );
                });
    }

    public IngresoExtraDTO saveIngresoExtra(IngresoExtraDTO dto) {
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
            if(entFinanExt == null){
                throw new RuntimeException("La entidad financiera no existe.");
            }
        } catch (Exception e){
            throw new RuntimeException("Entidad financiera no disponible.");
        }

        try {
            CategoriaDTO categoriaExterna = categoriaClient.obtenerCategoria(dto.getCategoriaId());
            if (categoriaExterna == null){
                throw new RuntimeException("La categoria no existe");
            }
        } catch (Exception e) {
            throw new RuntimeException("Categoria no disponible.");
        }

        IngresoExtra ingreso = new IngresoExtra();

        ingreso.setUsuarioId(dto.getUsuarioId());
        ingreso.setMonto(dto.getMonto());
        ingreso.setFecha(dto.getFecha());
        ingreso.setDescripcion(dto.getDescripcion());
        ingreso.setEntidadFinancieraId(dto.getEntidadFinancieraId());
        ingreso.setCategoriaId(dto.getCategoriaId());

        IngresoExtra nuevo = ingresoExtraRepository.save(ingreso);

        return new IngresoExtraDTO(
                nuevo.getId(),
                nuevo.getUsuarioId(),
                nuevo.getMonto(),
                nuevo.getFecha(),
                nuevo.getDescripcion(),
                nuevo.getEntidadFinancieraId(),
                nuevo.getCategoriaId()
        );
    }

    public IngresoExtraDTO updateIngresoExtra(Integer id, IngresoExtraDTO dto) {
        IngresoExtra existe = ingresoExtraRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Ingreso extra no encontrado"));

        try {
            UsuarioDTO usuarioExterno = usuarioClient.obtenerUsuario(dto.getUsuarioId());
            if (usuarioExterno == null){
                throw new RuntimeException("El usuario no existe");
            }
        } catch (Exception e) {
            throw new RuntimeException("Usuario no disponible");
        }

        try {
            EntidadFinancieraDTO entFinanExt = entFinanClient.obtenerEntFinanciera(dto.getEntidadFinancieraId());
            if(entFinanExt == null){
                throw new RuntimeException("La entidad financiera no existe.");
            }
        } catch (Exception e){
            throw new RuntimeException("Entidad financiera no disponible.");
        }

        try {
            CategoriaDTO categoriaExterna = categoriaClient.obtenerCategoria(dto.getCategoriaId());
            if (categoriaExterna == null){
                throw new RuntimeException("La categoria no existe");
            }
        } catch (Exception e) {
            throw new RuntimeException("Categoria no disponible.");
        }

        existe.setUsuarioId(dto.getUsuarioId());
        existe.setMonto(dto.getMonto());
        existe.setFecha(dto.getFecha());
        existe.setDescripcion(dto.getDescripcion());
        existe.setEntidadFinancieraId(dto.getEntidadFinancieraId());
        existe.setCategoriaId(dto.getCategoriaId());

        IngresoExtra nuevo = ingresoExtraRepository.save(existe);

        return new IngresoExtraDTO(
                nuevo.getId(),
                nuevo.getUsuarioId(),
                nuevo.getMonto(),
                nuevo.getFecha(),
                nuevo.getDescripcion(),
                nuevo.getEntidadFinancieraId(),
                nuevo.getCategoriaId()
        );
    }

    public void deleteIngresoExtra(Integer id) {
        if (ingresoExtraRepository.existsById(id)) {
            ingresoExtraRepository.deleteById(id);
        } else {
            throw new RuntimeException("Ingreso extra no encontrado");
        }
    }
}