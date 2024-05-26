package utn.frc.bda.examen.web.service.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utn.frc.bda.examen.domain.Alquiler;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AlquilerResponseGetPunto3 {
    Integer estado;

    public AlquilerResponseGetPunto3(Alquiler t) {
        this.estado=t.getEstado();
//        this.mediaType = t.getMediaType();
//        this.genre = t.getGenre();
//        this.composer = t.getComposer();
//        this.milliseconds = t.getMilliseconds();
//        this.bytes = t.getBytes();
//        this.unitPrice=t.getUnitPrice();
    }

}
