package cl.duoc.compras_service.service;

import cl.duoc.compras_service.client.UsuarioClient;
import cl.duoc.compras_service.dto.CompraFuturaDTO;
import cl.duoc.compras_service.dto.CompraFuturaResponseDTO;
import cl.duoc.compras_service.dto.UsuarioDTO;
import cl.duoc.compras_service.model.CompraFutura;
import cl.duoc.compras_service.repository.CompraFuturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompraFuturaService {

    @Autowired
    private CompraFuturaRepository compraFuRepository;

    // LISTAR
    public List<CompraFuturaDTO> getCompras() {
        return compraFuRepository.findAll()
                .stream()
                .map(compra ->
                        new CompraFuturaDTO(
                                compra.getUsuarioId(),
                                compra.getNombreProducto(),
                                compra.getPrecioEstimado(),
                                compra.getNota()
                        )
                )
                .toList();
    }

    // BUSCAR POR ID
    public Optional<CompraFuturaDTO> getCompra(Integer id) {
        return compraFuRepository.findById(id)
                .map(compra ->
                        new CompraFuturaDTO(
                                compra.getUsuarioId(),
                                compra.getNombreProducto(),
                                compra.getPrecioEstimado(),
                                compra.getNota()
                        )
                );
    }

    // GUARDAR
    public CompraFuturaDTO saveCompra(CompraFuturaDTO dto) {
        CompraFutura compra = new CompraFutura();
        compra.setUsuarioId(dto.getUsuarioId());
        compra.setNombreProducto(dto.getNombreProducto());
        compra.setPrecioEstimado(dto.getPrecioEstimado());
        compra.setNota(dto.getNota());

        CompraFutura guardada = compraFuRepository.save(compra);

        return new CompraFuturaDTO(
                guardada.getUsuarioId(),
                guardada.getNombreProducto(),
                guardada.getPrecioEstimado(),
                guardada.getNota()
        );
    }

    // ACTUALIZAR
    public CompraFuturaDTO updateCompra(Integer id, CompraFuturaDTO dto) {
        CompraFutura existe = compraFuRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Compra futura no encontrada"));

        existe.setUsuarioId(dto.getUsuarioId());
        existe.setNombreProducto(dto.getNombreProducto());
        existe.setPrecioEstimado(dto.getPrecioEstimado());
        existe.setNota(dto.getNota());

        CompraFutura actualizada = compraFuRepository.save(existe);

        return new CompraFuturaDTO(
                actualizada.getUsuarioId(),
                actualizada.getNombreProducto(),
                actualizada.getPrecioEstimado(),
                actualizada.getNota()
        );
    }

    // ELIMINAR
    public void deleteCompra(Integer id) {
        if (compraFuRepository.existsById(id)) {
            compraFuRepository.deleteById(id);
        } else {
            throw new RuntimeException("Compra futura no encontrada");
        }
    }

    @Autowired
    private UsuarioClient usuarioClient;

    public CompraFuturaResponseDTO getCompraConUsuario(Integer id) {
        CompraFutura compra = compraFuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compra no encontrada"));

        UsuarioDTO usuario = usuarioClient.obtenerUsuario(compra.getUsuarioId());

        return new CompraFuturaResponseDTO(
                compra.getId(),
                usuario,
                compra.getNombreProducto(),
                compra.getPrecioEstimado(),
                compra.getNota()
        );
    }

    public List<CompraFuturaResponseDTO> getComprasConUsuario() {
        return compraFuRepository.findAll()
                .stream()
                .map(compra -> {
                    UsuarioDTO usuario = usuarioClient.obtenerUsuario(compra.getUsuarioId());
                    return new CompraFuturaResponseDTO(
                            compra.getId(),
                            usuario,
                            compra.getNombreProducto(),
                            compra.getPrecioEstimado(),
                            compra.getNota()
                    );
                })
                .toList();
    }
}