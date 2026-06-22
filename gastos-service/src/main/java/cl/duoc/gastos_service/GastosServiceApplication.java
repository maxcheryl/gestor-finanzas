package cl.duoc.gastos_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class GastosServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GastosServiceApplication.class, args);
	}

}
