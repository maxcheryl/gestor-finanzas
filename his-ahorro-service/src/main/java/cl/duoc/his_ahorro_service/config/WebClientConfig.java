package cl.duoc.his_ahorro_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean (name = "usuarioWebClient")
    public WebClient usuarioWebClient() {

        return WebClient.builder()
                .baseUrl("http://localhost:8081")
                .build();
    }

    @Bean(name = "metaMensualWebClient")
    public WebClient metaMensualWebClient() {

        return WebClient.builder()
                .baseUrl("http://localhost:8087")
                .build();
    }
}