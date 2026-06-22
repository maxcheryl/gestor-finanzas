package cl.duoc.his_ahorro_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Historial de Ahorro")
                        .version("1.0")
                        .description("Microservicio para consultar y registrar ahorro acumulado"));
    }
}



