package cl.duoc.gastos_service.client;

import cl.duoc.gastos_service.dto.CategoriaDTO;
import cl.duoc.gastos_service.dto.CategoriaPersDTO;
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

    public CategoriaDTO obtenerCategoria(Integer id) {
        return webClient
                .get()
                .uri("/api/v1/categorias/{id}", id)
                .retrieve()
                .bodyToMono(CategoriaDTO.class)
                .block();
    }

    public List<CategoriaDTO> obtenerCategorias() {
        return webClient
                .get()
                .uri("/api/v1/categorias")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<CategoriaDTO>>() {})
                .block();
    }

    public CategoriaPersDTO obtenerCategoriaPers(Integer id) {
        return webClient
                .get()
                .uri("/api/v1/categorias-pers/{id}", id)
                .retrieve()
                .bodyToMono(CategoriaPersDTO.class)
                .block();
    }

    public List<CategoriaPersDTO> obtenerCategoriasPers() {
        return webClient
                .get()
                .uri("/api/v1/categorias-pers")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<CategoriaPersDTO>>() {})
                .block();
    }
}