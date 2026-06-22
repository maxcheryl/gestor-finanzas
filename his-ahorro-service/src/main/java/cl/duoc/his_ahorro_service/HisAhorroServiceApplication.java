package cl.duoc.his_ahorro_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class HisAhorroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HisAhorroServiceApplication.class, args);
	}

}
