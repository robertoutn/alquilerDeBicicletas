package ar.edu.utn.frc.bda.alquileres.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity(name = "ESTACIONES")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Estacion {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = true)
    private Integer id;
    @Basic
    @Column(name = "NOMBRE", nullable = true, length = 100)
    private String nombre;
    @Basic
    @Column(name = "FECHA_HORA_CREACION", nullable = true)
    private String fechaHoraCreacion;
    @Basic
    @Column(name = "LATITUD", nullable = true, precision = 0)
    private Float latitud;
    @Basic
    @Column(name = "LONGITUD", nullable = true, precision = 0)
    private Float longitud;

    public Estacion update(Estacion e) {
        this.id = e.id;
        this.nombre = e.nombre;
        this.fechaHoraCreacion = e.fechaHoraCreacion;
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
