package ar.edu.utn.frc.bda.alquileres.adapters;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AdapterConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


}
