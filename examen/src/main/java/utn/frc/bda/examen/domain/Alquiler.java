package utn.frc.bda.examen.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "ALQUILERES")
@Data
//@Getter @Setter @EqualsAndHashCode @ToString
@NoArgsConstructor
public class Alquiler {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = true)
    private Integer id;
    @Basic
    @Column(name = "ID_CLIENTE", nullable = true, length = 50)
    private Object idCliente;
    @Basic
    @Column(name = "ESTADO", nullable = true)
    private Integer estado;
//    @Basic
//    @Column(name = "ESTACION_RETIRO", nullable = true)
//    private Integer estacionRetiro;
//    @Basic
//    @Column(name = "ESTACION_DEVOLUCION", nullable = true)
//    private Integer estacionDevolucion;
    @Basic
    @Column(name = "FECHA_HORA_RETIRO", nullable = true)
    private Object fechaHoraRetiro;
    @Basic
    @Column(name = "FECHA_HORA_DEVOLUCION", nullable = true)
    private Object fechaHoraDevolucion;
    @Basic
    @Column(name = "MONTO", nullable = true, precision = 0)
    private Float monto;
//    @Basic
//    @Column(name = "ID_TARIFA", nullable = true)
//    private Integer idTarifa;

    @ManyToOne
    @JoinColumn(name = "ESTACION_RETIRO", referencedColumnName = "ID")
    private Estacion estacionesByEstacionRetiro;
    @ManyToOne
    @JoinColumn(name = "ESTACION_DEVOLUCION", referencedColumnName = "ID")
    private Estacion estacionesByEstacionDevolucion;
    @ManyToOne
    @JoinColumn(name = "ID_TARIFA", referencedColumnName = "ID")
    private Tarifa tarifasByIdTarifa;

    public void update(/*Object idCliente, */Integer estado/*, Object fechaHoraRetiro, Object fechaHoraDevolucion, Float monto, Estacion estacionesByEstacionRetiro, Estacion estacionesByEstacionDevolucion, Tarifa tarifasByIdTarifa*/) {
//        this.idCliente = idCliente;
        this.estado = estado;
//        this.fechaHoraRetiro = fechaHoraRetiro;
//        this.fechaHoraDevolucion = fechaHoraDevolucion;
//        this.monto = monto;
//        this.estacionesByEstacionRetiro = estacionesByEstacionRetiro;
//        this.estacionesByEstacionDevolucion = estacionesByEstacionDevolucion;
//        this.tarifasByIdTarifa = tarifasByIdTarifa;
    }
}
