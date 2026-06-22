package cl.duoc.ctas_finan_service.service;

import cl.duoc.ctas_finan_service.dto.MetodoPagoDTO;
import cl.duoc.ctas_finan_service.model.MetodoPago;
import cl.duoc.ctas_finan_service.repository.MetodoPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MetodoPagoService {

    @Autowired
    private MetodoPagoRepository repository;

    // LISTAR
    public List<MetodoPagoDTO> getMetodosPago() {

        return repository.findAll()
                .stream()
                .map(metodo -> new MetodoPagoDTO(
                        metodo.getId(),
                        metodo.getNombreMetodo()
                ))
                .toList();
    }

    // BUSCAR POR ID
    public Optional<MetodoPagoDTO> getMetodoPago(Integer id) {

        return repository.findById(id)
                .map(metodo -> new MetodoPagoDTO(
                        metodo.getId(),
                        metodo.getNombreMetodo()
                ));
    }

    // GUARDAR
    public MetodoPagoDTO saveMetodoPago(
            MetodoPagoDTO dto
    ) {

        MetodoPago metodo =
                new MetodoPago();

        metodo.setNombreMetodo(
                dto.getNombreMetodo());

        MetodoPago guardado =
                repository.save(metodo);

        return new MetodoPagoDTO(
                guardado.getId(),
                guardado.getNombreMetodo()
        );
    }

    // ACTUALIZAR
    public MetodoPagoDTO updateMetodoPago(
            Integer id,
            MetodoPagoDTO dto
    ) {

        MetodoPago existe =
                repository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Método de pago no encontrado"
                                ));

        existe.setNombreMetodo(
                dto.getNombreMetodo());

        MetodoPago actualizado =
                repository.save(existe);

        return new MetodoPagoDTO(
                actualizado.getId(),
                actualizado.getNombreMetodo()
        );
    }

    // ELIMINAR
    public void deleteMetodoPago(
            Integer id
    ) {

        if (repository.existsById(id)) {

            repository.deleteById(id);

        } else {

            throw new RuntimeException(
                    "Método de pago no encontrado"
            );
        }
    }
}