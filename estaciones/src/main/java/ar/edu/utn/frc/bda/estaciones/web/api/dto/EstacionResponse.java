package ar.edu.utn.frc.bda.estaciones.web.api.dto;

import ar.edu.utn.frc.bda.estaciones.domain.Estacion;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data @AllArgsConstructor
public class EstacionResponse {

    Integer id;
    String nombre;
    LocalDateTime fechaHoraCreacion;
    double latitud;
    double longitud;

    public EstacionResponse(Estacion e) {
        this.id = e.getId();
        this.nombre = e.getNombre();
        this.fechaHoraCreacion = e.getFechaHoraCreacion();
        this.latitud = e.getLatitud();
        this.longitud = e.getLongitud();
    }
}
