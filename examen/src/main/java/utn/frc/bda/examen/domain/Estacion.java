package utn.frc.bda.examen.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "ESTACIONES")
@Data
@NoArgsConstructor
public class Estacion {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = true)
    private Integer id;
    @Basic
    @Column(name = "NOMBRE", nullable = true, length = 100)
    private Object nombre;
    @Basic
    @Column(name = "FECHA_HORA_CREACION", nullable = true)
    private Object fechaHoraCreacion;
    @Basic
    @Column(name = "LATITUD", nullable = true, precision = 0)
    private Float latitud;
    @Basic
    @Column(name = "LONGITUD", nullable = true, precision = 0)
    private Float longitud;

    @JsonIgnore
    @OneToMany(mappedBy = "estacionesByEstacionRetiro")
    private List<Alquiler> alquilerById;

    @JsonIgnore
    @OneToMany(mappedBy = "estacionesByEstacionDevolucion")
    private List<Alquiler> alquilerById_0;

    public Estacion(Integer id, Object nombre, Object fechaHoraCreacion, Float latitud, Float longitud) {
        this.id = id;
        this.nombre = nombre;
        this.fechaHoraCreacion = fechaHoraCreacion;
        this.latitud = latitud;
        this.longitud = longitud;
    }
}
