package ar.edu.utn.frc.bda.estaciones.web.api.dto;

import ar.edu.utn.frc.bda.estaciones.domain.Estacion;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data @AllArgsConstructor
public class EstacionResponsePunto3DTO {

    double distanciaTotal;
    Estacion estacion;
    double distancia;

//    public EstacionResponsePunto3DTO( Estacion e, double distancia) {
//        this.estacion = e;
//        this.distancia=distancia;
//    }

    public EstacionResponsePunto3DTO update(double distanciaTotal, Estacion e, double distancia) {
        this.distanciaTotal=distanciaTotal;
        this.estacion = e;
        this.distancia=distancia;
        return this;
    }


}
