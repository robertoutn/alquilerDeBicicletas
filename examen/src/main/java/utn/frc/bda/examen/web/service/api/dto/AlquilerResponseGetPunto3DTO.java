package utn.frc.bda.examen.web.service.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @NoArgsConstructor
@AllArgsConstructor
public class AlquilerResponseGetPunto3DTO {

    String albumTiltle;
    List<AlquilerResponseGetPunto3> alquilerResponseGetPunto3List;

}

