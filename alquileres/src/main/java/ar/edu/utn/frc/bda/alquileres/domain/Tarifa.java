package ar.edu.utn.frc.bda.alquileres.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.Month;
import java.time.Year;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Entity(name = "TARIFAS")
public class Tarifa {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    @Column(name = "ID", nullable = true)
    private Integer id;
    @Basic
    @Column(name = "TIPO_TARIFA", nullable = true)
    private Integer tipoTarifa;
    @Basic
    @Column(name = "DEFINICION", nullable = true, length = 1)
    private char definicion;
    @Basic
    @Column(name = "DIA_SEMANA", nullable = true)
    private Integer diaSemana;
    @Basic
    @Column(name = "DIA_MES", nullable = true)
    private Integer diaMes;
    @Basic
    @Column(name = "MES", nullable = true)
    private Integer mes;
    @Basic
    @Column(name = "ANIO", nullable = true)
    private Integer anio;
    @Basic
    @Column(name = "MONTO_FIJO_ALQUILER", nullable = true, precision = 0)
    private Float montoFijoAlquiler;
    @Basic
    @Column(name = "MONTO_MINUTO_FRACCION", nullable = true, precision = 0)
    private Float montoMinutoFraccion;
    @Basic
    @Column(name = "MONTO_KM", nullable = true, precision = 0)
    private Float montoKm;
    @Basic
    @Column(name = "MONTO_HORA", nullable = true, precision = 0)
    private Float montoHora;


}
