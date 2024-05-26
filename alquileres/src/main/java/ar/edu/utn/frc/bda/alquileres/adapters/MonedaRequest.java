package ar.edu.utn.frc.bda.alquileres.adapters;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MonedaRequest {

    String moneda_destino;
    Double importe;
}
