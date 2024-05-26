package ar.edu.utn.frc.bda.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtGrantedAuthoritiesConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;


@Configuration
@EnableWebFluxSecurity
public class GWConfig {

    @Bean
    public RouteLocator configurarRutas(RouteLocatorBuilder builder,
                                        @Value("${URL_API_ALQUILERES}") String uriAlquileres,
                                        @Value("${URL_API_ESTACIONES}") String uriEstaciones) {
        return builder.routes()
                // Ruteo al Microservicio de Alquileres
                .route(p -> p
                        .path("/api/alquiler/**")
                        .uri(uriAlquileres))
                // Ruteo al Microservicio de Estaciones
                .route(p -> p
                        .path("/api/estacion/**")
                        .uri(uriEstaciones))
                .build();
    }

    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http) throws Exception {
        http.authorizeExchange(exchanges -> exchanges

//CLIENTE
                        .pathMatchers(HttpMethod.POST,"/api/alquiler/**")
                        .hasRole("CLIENTE")

                        .pathMatchers(HttpMethod.PUT,"/api/alquiler/**")
                        .hasRole("CLIENTE")

                        .pathMatchers(HttpMethod.GET,"/api/estacion/**")
                        .hasRole("CLIENTE")

//ADMINISTRADOR
                        .pathMatchers(HttpMethod.GET,"/api/alquiler/**")
                        .hasRole("ADMINISTRADOR")

                        .pathMatchers(HttpMethod.POST,"/api/estacion/**")
                        .hasRole("ADMINISTRADOR")


                        // Cualquier otra petición...
                        .anyExchange()
                        .authenticated()


                ).oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .csrf(csrf -> csrf.disable());
        return http.build();
    }

    @Bean
    public ReactiveJwtAuthenticationConverter jwtAuthenticationConverter() {
        var jwtAuthenticationConverter = new ReactiveJwtAuthenticationConverter();
        var grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

        // Se especifica el nombre del claim a analizar
        grantedAuthoritiesConverter.setAuthoritiesClaimName("authorities");
        // Se agrega este prefijo en la conversión por una convención de Spring
        grantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");

        // Se asocia el conversor de Authorities al Bean que convierte el token JWT a un objeto Authorization
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(
                new ReactiveJwtGrantedAuthoritiesConverterAdapter(grantedAuthoritiesConverter));
        // También se puede cambiar el claim que corresponde al nombre que luego se utilizará en el objeto
        // Authorization
        // jwtAuthenticationConverter.setPrincipalClaimName("user_name");

        return jwtAuthenticationConverter;
    }


}
