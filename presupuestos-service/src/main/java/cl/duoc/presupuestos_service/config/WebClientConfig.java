package cl.duoc.presupuestos_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean(name = "gastoWebClient")
    public WebClient gastoWebClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8084")
                .build();
    }

    @Bean(name = "usuarioWebClient")
    public WebClient usuarioWebClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8081")
                .build();
    }
}
