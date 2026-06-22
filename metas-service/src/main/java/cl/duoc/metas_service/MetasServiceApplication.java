package cl.duoc.metas_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MetasServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetasServiceApplication.class, args);
	}

}
