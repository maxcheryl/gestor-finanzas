package cl.duoc.ingresos_service.client;

import cl.duoc.ingresos_service.dto.EntidadFinancieraDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class EntFinanClient {
    @Autowired
    @Qualifier("entFinancieraWebClient")

    private WebClient webClient;

    public EntidadFinancieraDTO obtenerEntFinanciera(Integer id){
        return webClient
                .get()
                .uri("/api/v1/entidades-financieras/{id}", id)
                .retrieve()
                .bodyToMono(EntidadFinancieraDTO.class)
                .block();
    }

    public List<EntidadFinancieraDTO> obtenerEntFinancieras(){
        return webClient
                .get()
                .uri("/api/v1/entidades-financieras")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<EntidadFinancieraDTO>>() {})
                .block();
    }
}
