package ar.edu.utn.frc.bda.alquileres.web.api.dto;

import ar.edu.utn.frc.bda.alquileres.domain.Alquiler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder

public class AlquilerResponse {
    private Integer id;
    private String idCliente;
    private int estado;


    private Integer estacionRetiro;

    private Integer estacionDevolucion;
    private LocalDateTime fechaHoraRetiro;
    private LocalDateTime fechaHoraDevolucion;
    private String monto;
    private Integer idTarifa;

}
