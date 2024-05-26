package utn.frc.bda.examen.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "TARIFAS")
@Data
@NoArgsConstructor
public class Tarifa {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = true)
    private Integer id;
    @Basic
    @Column(name = "TIPO_TARIFA", nullable = true)
    private Integer tipoTarifa;
    @Basic
    @Column(name = "DEFINICION", nullable = true, length = 1)
    private Object definicion;
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

    @JsonIgnore
    @OneToMany(mappedBy = "tarifasByIdTarifa")
    private List<Alquiler> alquilerById;

    public Tarifa(Integer id, Integer tipoTarifa, Object definicion, Integer diaSemana, Integer diaMes, Integer mes, Integer anio, Float montoFijoAlquiler, Float montoMinutoFraccion, Float montoKm, Float montoHora) {
        this.id = id;
        this.tipoTarifa = tipoTarifa;
        this.definicion = definicion;
        this.diaSemana = diaSemana;
        this.diaMes = diaMes;
        this.mes = mes;
        this.anio = anio;
        this.montoFijoAlquiler = montoFijoAlquiler;
        this.montoMinutoFraccion = montoMinutoFraccion;
        this.montoKm = montoKm;
        this.montoHora = montoHora;
    }
}
