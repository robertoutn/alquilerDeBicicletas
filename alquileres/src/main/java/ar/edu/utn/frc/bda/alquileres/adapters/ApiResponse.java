package ar.edu.utn.frc.bda.alquileres.adapters;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private T data;

}
