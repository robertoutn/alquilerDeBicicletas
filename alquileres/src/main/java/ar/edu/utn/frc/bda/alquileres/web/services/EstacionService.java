package ar.edu.utn.frc.bda.alquileres.web.services;

import ar.edu.utn.frc.bda.alquileres.adapters.ApiResponse;
import ar.edu.utn.frc.bda.alquileres.domain.Estacion;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class EstacionService {
    private RestTemplate template;

    public EstacionService(RestTemplate template) {
        this.template = template;
    }

    public Optional<Estacion> getEstacionById(Integer id) {
        ResponseEntity<ApiResponse<Estacion>> res = template.exchange(
                "http://localhost:8081/api/estacion/{id}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<Estacion>>() {},
                id
        );

        if (res.getStatusCode().is2xxSuccessful()) {
            return Optional.ofNullable(res.getBody().getData());
        }
        return Optional.empty();
    }



}
