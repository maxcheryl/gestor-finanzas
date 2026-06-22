package cl.duoc.presupuestos_service.client;

import cl.duoc.presupuestos_service.dto.GastoDTO;
import cl.duoc.presupuestos_service.dto.GastoRecurrenteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class GastoClient {

    @Autowired
    @Qualifier("gastoWebClient")
    private WebClient webClient;

    public List<GastoDTO> obtenerGastos() {
        return webClient
                .get()
                .uri("/api/v1/gastos")
                .retrieve()
                .bodyToFlux(GastoDTO.class)
                .collectList()
                .block();
    }

    public List<GastoRecurrenteDTO> obtenerGastosRecurrentes() {
        return webClient
                .get()
                .uri("/api/v1/gastos-recurrentes")
                .retrieve()
                .bodyToFlux(GastoRecurrenteDTO.class)
                .collectList()
                .block();
    }
}
