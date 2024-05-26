package utn.frc.bda.examen.web.service.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utn.frc.bda.examen.domain.Alquiler;

//        El resultado debe informar: TrackId, Name, Seconds y UnitPrice de cada
//                uno de los tracks retornados (se sugiere crear un dto con esta estructura).

@Data @NoArgsConstructor
@AllArgsConstructor
public class AlquilerResponseGetPunto1DTO {
    Integer alquilerId;
    Integer estado;
//    Integer seconds;
//    BigDecimal unitPrice;

    public AlquilerResponseGetPunto1DTO(Alquiler t) {
        this.alquilerId = t.getId();
        this.estado=t.getEstado();
//        this.seconds =track.getMilliseconds()/1000;
//        this.unitPrice=track.getUnitPrice();
    }
}
