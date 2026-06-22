package cl.duoc.his_ahorro_service.client;

import cl.duoc.his_ahorro_service.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class UsuarioClient {

    @Autowired
    @Qualifier("usuarioWebClient")
    private WebClient webClient;

    public UsuarioDTO obtenerUsuario(Integer id) {

        return webClient
                .get()
                .uri("/api/v1/usuarios/{id}", id)
                .retrieve()
                .bodyToMono(UsuarioDTO.class)
                .block();
    }
}