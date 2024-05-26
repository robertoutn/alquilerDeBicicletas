package ar.edu.utn.frc.bda.alquileres.adapters;


import ar.edu.utn.frc.bda.alquileres.web.services.MonedaService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class MonedaAdapter implements MonedaService {

    private RestTemplate template;

    public MonedaAdapter(RestTemplate template) {
        this.template = template;
    }

    @Override
    public double convertirMoneda(double monto, String monedaDestino) {
        ResponseEntity<MonedaResponse> res = template.postForEntity(
                "http://34.82.105.125:8080/convertir",
                new MonedaRequest(monedaDestino,monto),
                MonedaResponse.class
        );
        if (res.getStatusCode().is2xxSuccessful()) {
            return Objects.requireNonNull(res.getBody()).getImporte();
        }
        return 0;

    }
}
