package ar.edu.utn.frc.bda.alquileres.web.api;

import ar.edu.utn.frc.bda.alquileres.domain.Alquiler;
import ar.edu.utn.frc.bda.alquileres.web.api.dto.*;
import ar.edu.utn.frc.bda.alquileres.web.services.AlquilerService;
import ar.edu.utn.frc.bda.alquileres.web.services.MonedaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/alquiler")
public class AlquilerAPI {

    private final AlquilerService alquilerService;
    private final MonedaService monedaService;

    public AlquilerAPI(AlquilerService alquilerService, MonedaService monedaService) {
        this.alquilerService = alquilerService;
        this.monedaService = monedaService;
    }


    @GetMapping()
    public ResponseEntity<Object> getAlquilerConFiltro(@RequestBody/*(required = false)*/ AlquilerFiltroRequest request ) {
        List<Alquiler> alquiler = alquilerService.getAlquilerConFiltro(
                request.getIdCliente(),
                request.getEstado(),
                request.getEstacionRetiro(),
                request.getEstacionDevolucion());
        return alquiler.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(alquiler);
    }

    @PostMapping("/iniciar")
    public ResponseEntity<AlquilerResponse> alquilar(
            @RequestParam (name = "idEstacionRetiro") Integer idEstacionRetiro,
            @RequestParam (name = "idCliente") String idCliente
    ) {

        Optional<Alquiler> alquiler = alquilerService.alquilarBicicleta(
                idEstacionRetiro, idCliente
        );

        return alquiler.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(new AlquilerResponse(
                alquiler.get().getId(),
                alquiler.get().getIdCliente(),
                alquiler.get().getEstado(),
                alquiler.get().getEstacionRetiro(),
                null,
                alquiler.get().getFechaHoraRetiro(),
                alquiler.get().getFechaHoraDevolucion(),
                null,
                null
        ));
    }

    @PutMapping("/finalizar")
    public ResponseEntity<Object> devolverBicicleta(
            @RequestBody(required = false) AlquilerRequestPUT request) {
        Optional<Alquiler> alquiler = alquilerService.finalizarAlquiler(
                request.getIdEstacionDevolucion(), request.getIdAlquiler());
        double monto = 0;
        if (request.getMoneda() == null || request.getMoneda() == Moneda.ARS) {
            request.setMoneda(Moneda.ARS);
            monto = alquiler.get().getMonto();
        }else {
            monto = monedaService.convertirMoneda(
                    alquiler.get().getMonto(), request.getMoneda().getValor());
        }
        long montoFinal = Math.round(monto);

        if (montoFinal == 0){
            return ResponseEntity.badRequest().build();
        }

        return alquiler.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(new AlquilerResponse(
                alquiler.get().getId(),
                alquiler.get().getIdCliente(),
                alquiler.get().getEstado(),
                alquiler.get().getEstacionRetiro(),
                alquiler.get().getEstacionDevolucion(),
                alquiler.get().getFechaHoraRetiro(),
                alquiler.get().getFechaHoraDevolucion(),
                montoFinal + " " + request.getMoneda(),
                alquiler.get().getIdTarifa().getId()
        ));

    }


}
