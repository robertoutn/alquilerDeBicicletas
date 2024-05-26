package ar.edu.utn.frc.bda.alquileres.web.api.dto;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AlquilerFiltroRequest {
    @Nullable
    private String idCliente;
    @Nullable
    private Integer estado;
    @Nullable
    private Integer estacionRetiro;
    @Nullable
    private Integer estacionDevolucion;


}
