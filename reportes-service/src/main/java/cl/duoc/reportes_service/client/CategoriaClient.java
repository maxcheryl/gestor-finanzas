package cl.duoc.reportes_service.client;

import cl.duoc.reportes_service.dto.CategoriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class CategoriaClient {

    @Autowired
    @Qualifier("categoriaWebClient")
    private WebClient webClient;

    public CategoriaDTO obtenerCategoria(Integer id) { // BUSCAR POR ID

        return webClient
                .get()
                .uri("/api/v1/categorias/{id}", id)
                .retrieve()
                .bodyToMono(CategoriaDTO.class)
                .block();
    }

    // LISTAR TODAS
    public List<CategoriaDTO> obtenerCategorias() {
        return webClient
                .get()
                .uri("/api/v1/categorias")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<CategoriaDTO>>() {})
                .block();
    }
}