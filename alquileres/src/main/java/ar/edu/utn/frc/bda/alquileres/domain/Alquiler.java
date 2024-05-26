package ar.edu.utn.frc.bda.alquileres.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
//@ToString
//@AllArgsConstructor
@Entity(name = "ALQUILERES")
public class Alquiler {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    @Column(name = "ID", nullable = true)
    private Integer id;
    @Basic
    @Column(name = "ID_CLIENTE", nullable = true, length = 50)
    private String idCliente;
    @Basic
    @Column(name = "ESTADO", nullable = true)
    private int estado;

    @Basic
    @Column(name = "ESTACION_RETIRO", nullable = true/*, insertable=false, updatable=false*/)
    private Integer estacionRetiro;
    @Basic
    @Column(name = "ESTACION_DEVOLUCION", nullable = true/*, insertable=false, updatable=false*/)
    private Integer estacionDevolucion;

    @Basic
    @Column(name = "FECHA_HORA_RETIRO", nullable = true)
    private LocalDateTime fechaHoraRetiro;
    @Basic
    @Column(name = "FECHA_HORA_DEVOLUCION", nullable = true)
    private LocalDateTime fechaHoraDevolucion;
    @Basic
    @Column(name = "MONTO", nullable = true, precision = 0)
    private Float monto;

    @OneToOne
    @JoinColumn(name = "ID_TARIFA")
    private Tarifa idTarifa;

    public Alquiler(/*Integer id,*/ String idCliente, int estado, Integer estacionRetiro, Integer estacionDevolucion, LocalDateTime fechaHoraRetiro, LocalDateTime fechaHoraDevolucion, Float monto, Tarifa idTarifa) {
//        this.id = id;
        this.idCliente = idCliente;
        this.estado = estado;
        this.estacionRetiro = estacionRetiro;
        this.estacionDevolucion = estacionDevolucion;
        this.fechaHoraRetiro = fechaHoraRetiro;
        this.fechaHoraDevolucion = fechaHoraDevolucion;
        this.monto = monto;
        this.idTarifa = idTarifa;
    }
}
