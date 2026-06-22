package cl.duoc.compras_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ComprasServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComprasServiceApplication.class, args);
	}

}
