package cl.duoc.his_ahorro_service.client;

import cl.duoc.his_ahorro_service.dto.MetaMensualDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MetaMensualClient {

    @Autowired
    @Qualifier("metaMensualWebClient")
    private WebClient webClient;

    public MetaMensualDTO obtenerMeta(Integer id) {

        return webClient
                .get()
                .uri("/api/v1/metas/{id}", id)
                .retrieve()
                .bodyToMono(MetaMensualDTO.class)
                .block();
    }
}