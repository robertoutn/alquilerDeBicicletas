package ar.edu.utn.frc.bda.estaciones.web.api.dto;

import ar.edu.utn.frc.bda.estaciones.domain.Estacion;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class EstacionRequest {
    private String nombre;
    private double latitud;
    private double longitud;


    public Estacion toEstacion(/*String nombre, double latitud, double longitud*/) {
        return new Estacion(
        nombre,
        latitud,
        longitud
        );
    }
}
