package ar.edu.utn.frc.bda.alquileres.web.api.dto;

import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public class AlquilerRequestPUT {

    private Integer idAlquiler;
    private Integer idEstacionDevolucion;
    @Nullable
    private Moneda moneda;


}
