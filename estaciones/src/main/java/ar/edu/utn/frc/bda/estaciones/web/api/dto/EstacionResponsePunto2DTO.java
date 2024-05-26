package ar.edu.utn.frc.bda.estaciones.web.api.dto;

import ar.edu.utn.frc.bda.estaciones.domain.Estacion;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data @AllArgsConstructor
public class EstacionResponsePunto2DTO {

    Integer id;
    String nombre;
    LocalDateTime fechaHoraCreacion;
    double latitud;
    double longitud;
    double distancia;

    public EstacionResponsePunto2DTO(Estacion e, double distancia) {
        this.id = e.getId();
        this.nombre = e.getNombre();
        this.fechaHoraCreacion = e.getFechaHoraCreacion();
        this.latitud = e.getLatitud();
        this.longitud = e.getLongitud();
        this.distancia=distancia;
    }
}
