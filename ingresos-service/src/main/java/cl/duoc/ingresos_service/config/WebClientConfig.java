package cl.duoc.ingresos_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean(name = "usuariosWebClient")
    public WebClient usuarioswebClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8081")
                .build();
    }

    @Bean(name = "entFinancieraWebClient")
    public WebClient entFinancieraWebClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8082")
                .build();
    }

    @Bean(name = "categoriaWebClient")
    public WebClient categoriaWebClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8088")
                .build();
    }

}