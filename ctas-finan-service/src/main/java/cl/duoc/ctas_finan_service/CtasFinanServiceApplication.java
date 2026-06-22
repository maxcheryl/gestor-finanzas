package cl.duoc.ctas_finan_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CtasFinanServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CtasFinanServiceApplication.class, args);
	}

}
