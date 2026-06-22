package cl.duoc.metas_service.client;

import cl.duoc.metas_service.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class UsuarioClient {

    @Autowired
    @Qualifier("usuariosWebClient")
    private WebClient webClient;

    public UsuarioDTO obtenerUsuario(Integer id) {
        return webClient
                .get()
                .uri("/api/v1/usuarios/{id}", id)
                .retrieve()
                .bodyToMono(UsuarioDTO.class)
                .block();
    }

    public List<UsuarioDTO> obtenerUsuarios() {
        return webClient
                .get()
                .uri("/api/v1/usuarios")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<UsuarioDTO>>() {})
                .block();
    }
}