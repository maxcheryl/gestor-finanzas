package cl.duoc.categorias_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CategoriasServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CategoriasServiceApplication.class, args);
	}

}
