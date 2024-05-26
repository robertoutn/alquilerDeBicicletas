package ar.edu.utn.frc.bda.alquileres.web.services;

import ar.edu.utn.frc.bda.alquileres.domain.Alquiler;
import ar.edu.utn.frc.bda.alquileres.domain.Estacion;
import ar.edu.utn.frc.bda.alquileres.domain.Tarifa;
import ar.edu.utn.frc.bda.alquileres.repositories.TarifaRepositoty;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
@Service
public class TarifaService {
    private final TarifaRepositoty tarifaRepository;
    private final EstacionService estacionService;

    public TarifaService(TarifaRepositoty tarifaRepository, EstacionService estacionService) {
        this.tarifaRepository = tarifaRepository;
        this.estacionService = estacionService;
    }

      public Tarifa getTarifa(){
        LocalDateTime fecha_actual = LocalDateTime.now();
        //Obtener tarifa segun si es normal o con descuento
        Optional<Tarifa> optionalTarifa =tarifaRepository.getTarifaByDiaMesAndMesAndAnio(
                fecha_actual.getDayOfMonth(),
                fecha_actual.getMonth().getValue(),
                fecha_actual.getYear());
        if(optionalTarifa.isPresent()){
            return optionalTarifa.get();
        }else {
            return tarifaRepository.getTarifaByDiaSemana(fecha_actual.getDayOfWeek().getValue());

        }
    }

    public Float calcularTarifa(Alquiler alquiler, Tarifa tarifa) {
        LocalDateTime fecha_actual = LocalDateTime.now();
        Duration duracion = Duration.between(alquiler.getFechaHoraRetiro(), fecha_actual);

        //cantidad de horas y minutos que duro el alquiler
        Long duracion_horas = duracion.toHours();
        Long duracion_minutos = duracion.toMinutes()- (duracion_horas*60);

        //Tarifas
        double tarifa_total = 0;

        //monto fijo
        tarifa_total += tarifa.getMontoFijoAlquiler();

        //monto por hora y minutos
        if(duracion_minutos >= 31 ){
            tarifa_total += tarifa.getMontoHora() * (duracion_horas + 1);
        }else {
            tarifa_total += tarifa.getMontoHora() * duracion_horas;
            tarifa_total += tarifa.getMontoMinutoFraccion() * duracion_minutos;
        }

        //monto por distancia
        Estacion estacion_retiro = estacionService.getEstacionById(alquiler.getEstacionRetiro()).get();
        Estacion estacion_devolucion = estacionService.getEstacionById(alquiler.getEstacionDevolucion()).get();

        float distancia=calcularDistancia(
                estacion_retiro.getLatitud(),
                estacion_retiro.getLongitud(),
                estacion_devolucion.getLatitud(),
                estacion_devolucion.getLongitud());
        tarifa_total += distancia * tarifa.getMontoKm();

        return (float) tarifa_total;
    }


    public float calcularDistancia(double lat1, double lon1, double lat2, double lon2) {
        // Conversión de grados a metros (asumiendo que 1 grado = 110,000 metros)
        double LatitudMetros = Math.abs(lat1 - lat2) * 110;
        double LongitudMetros = Math.abs(lon1 - lon2) * 110;

        // Distancia euclídea
        return (float) Math.sqrt(Math.pow(LatitudMetros, 2) + Math.pow(LongitudMetros, 2));

    }

}
