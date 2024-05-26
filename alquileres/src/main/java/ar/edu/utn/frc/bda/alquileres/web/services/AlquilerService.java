package ar.edu.utn.frc.bda.alquileres.web.services;

//import ar.edu.utn.frc.bda.alquileres.adapters.EstacionService;
import ar.edu.utn.frc.bda.alquileres.domain.Alquiler;
import ar.edu.utn.frc.bda.alquileres.domain.EstadoAlquiler;
import ar.edu.utn.frc.bda.alquileres.domain.Tarifa;
import ar.edu.utn.frc.bda.alquileres.repositories.AlquilerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AlquilerService {

    private final AlquilerRepository alquilerRepository;
//    private final TarifaRepositoty tarifaRepository;
    private final TarifaService tarifaService;

//    private final EstacionService estacionService;
    private final EstacionService estacionService;

    public AlquilerService(AlquilerRepository alquilerRepository, TarifaService tarifaService, EstacionService estacionService) {
        this.alquilerRepository = alquilerRepository;
        this.tarifaService = tarifaService;
        this.estacionService = estacionService;
    }

    public Alquiler findById(Integer alquilerId) throws Exception {
        Alquiler alquiler = alquilerRepository.findById(alquilerId).orElseThrow(()->
                new Exception("No se encontro alquiler"));

        return alquiler;
    }

   public List<Alquiler> getAlquilerConFiltro(String idCliente, Integer estado, Integer estacionRetiro,
                                                         Integer estacionDevolucion) {
       List<Alquiler> lista = alquilerRepository.findByParameters(idCliente, estado, estacionRetiro, estacionDevolucion);
        return lista;
    }

    public Optional<Alquiler> alquilarBicicleta(Integer idEstacionRetiro, String idCliente) {
        if (estacionService.getEstacionById(idEstacionRetiro).isEmpty()) {
            return Optional.empty();
        }
        Alquiler alquiler = new Alquiler(idCliente,
                EstadoAlquiler.INICIADO.getValor(),
                idEstacionRetiro, null,
                LocalDateTime.now(), null,
                0F,
                null);
        return Optional.of(alquilerRepository.save(alquiler));

    }

    public Optional<Alquiler> finalizarAlquiler(Integer idEstacion, Integer idAlquiler) {
        if (estacionService.getEstacionById(idEstacion).isEmpty()) {
            return Optional.empty();
        }
        Tarifa tarifa = tarifaService.getTarifa();

        Alquiler alquiler = alquilerRepository.findById(idAlquiler).get();

        alquiler.setEstacionDevolucion(idEstacion);
        alquiler.setFechaHoraDevolucion(LocalDateTime.now());
        alquiler.setEstado(EstadoAlquiler.FINALIZADO.getValor());
        alquiler.setMonto(tarifaService.calcularTarifa(alquiler,tarifa));
        alquiler.setIdTarifa(tarifa);
        return Optional.of(alquilerRepository.save(alquiler));
    }


}
