package ar.edu.utn.frc.bda.estaciones.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "ESTACIONES")
@Getter
@Setter
@NoArgsConstructor
@ToString

@SequenceGenerator(name = "tu_secuencia_generator", sequenceName = "tu_secuencia", allocationSize = 19)
public class Estacion {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tu_secuencia_generator")
    @Id
    @Column(name = "ID", nullable = true)
    private Integer id;
    @Basic
    @Column(name = "NOMBRE", nullable = true, length = 100)
    private String nombre;
    @Basic
    @Column(name = "FECHA_HORA_CREACION", nullable = true)
    private LocalDateTime fechaHoraCreacion;
    @Basic
    @Column(name = "LATITUD", nullable = true, precision = 0)
    private double latitud;
    @Basic
    @Column(name = "LONGITUD", nullable = true, precision = 0)
    private double longitud;

//    @JsonIgnore
//    @OneToMany(mappedBy = "estacionByEstacionRetiro")
//    private Set<Alquiler> alquilerById = new HashSet<>();
//
//    @JsonIgnore
//    @OneToMany(mappedBy = "estacionByEstacionDevolucion")
//    private Set<Alquiler> alquilerById_0 = new HashSet<>();

    public Estacion(/*Integer id,*/ String nombre,/* LocalDateTime fechaHoraCreacion,*/ double latitud, double longitud) {
//        this.id = id;
        this.nombre = nombre;
        this.fechaHoraCreacion = LocalDateTime.now();
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Estacion(Estacion e) {
        this.id = e.getId();
        this.nombre = e.getNombre();
        this.fechaHoraCreacion = e.getFechaHoraCreacion();
        this.latitud = e.getLatitud();
        this.longitud = e.getLongitud();
    }

    public Estacion update(Estacion e) {
//        this.id = e.id;
        this.nombre = e.nombre;
//        this.fechaHoraCreacion = e.fechaHoraCreacion;
        this.latitud = e.latitud;
        this.longitud = e.longitud;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estacion that = (Estacion) o;
        return Objects.equals(id, that.id) && Objects.equals(nombre, that.nombre) && Objects.equals(fechaHoraCreacion, that.fechaHoraCreacion) && Objects.equals(latitud, that.latitud) && Objects.equals(longitud, that.longitud);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, fechaHoraCreacion, latitud, longitud);
    }

}
