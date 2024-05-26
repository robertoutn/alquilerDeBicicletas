package ar.edu.utn.frc.bda.estaciones.web.services;

import ar.edu.utn.frc.bda.estaciones.domain.Estacion;
import ar.edu.utn.frc.bda.estaciones.repositories.EstacionRepository;
import ar.edu.utn.frc.bda.estaciones.web.api.dto.EstacionRequest;
import ar.edu.utn.frc.bda.estaciones.web.api.dto.EstacionResponse;
import ar.edu.utn.frc.bda.estaciones.web.api.dto.EstacionResponsePunto2DTO;
import ar.edu.utn.frc.bda.estaciones.web.api.dto.EstacionResponsePunto3DTO;
import ar.edu.utn.frc.bda.estaciones.web.api.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EstacionService {
    private final EstacionRepository estacionRepository;

    public EstacionService(EstacionRepository estacionRepository) {
        this.estacionRepository = estacionRepository;
    }

    public List<Estacion> findAll() {
        return estacionRepository.findAll();
    }

    public Optional<Estacion> getEstacionById(Integer id) {
        Optional<Estacion> estacion = estacionRepository.findById(id);

        return estacion;
    }

    public Optional<Estacion> addEstacion(EstacionRequest e) {
        Estacion estacion = new Estacion(e.getNombre(), e.getLatitud(), e.getLongitud());
        return Optional.of(estacionRepository.save(estacion));
    }

    public Optional<EstacionResponsePunto2DTO> add(LocalDateTime fecha, double lat) {

        List<Estacion> estacions = estacionRepository.Punto2(fecha, lat);

        List<Estacion> estacionList = new ArrayList<>();
        int contSeconds = 0;
        int i = 0;
        Estacion estacionMasLejana1 = null;
        Estacion estacionMasLejana2 = null;
        double distanciaMasLejana = Double.MIN_VALUE;


        // Agregar estacions hasta alcanzar la cantidad de playlistSeconds
        while (i < estacions.size() - 1 /*&& contSeconds < playlistSeconds*/) {
            Estacion estacion1 = estacions.get(i);
            Estacion estacion2 = estacions.get(i + 1);

            double distancia = euclideanDistance(
                    estacion1.getLatitud(), estacion1.getLongitud()
                    , estacion2.getLatitud(), estacion2.getLongitud());

            if (distancia > distanciaMasLejana) {
                distanciaMasLejana = distancia;
                estacionMasLejana1 = estacion1;
                estacionMasLejana2 = estacion2;
            }

            i++;
        }
        Estacion estacion = new Estacion("Estacion media",
                (estacionMasLejana1.getLatitud() + estacionMasLejana2.getLatitud()) / 2,
                (estacionMasLejana2.getLongitud() + estacionMasLejana2.getLongitud()) / 2
        );

        estacionRepository.save(estacion);
        EstacionResponsePunto2DTO estacionResponsePunto2DTO =
                new EstacionResponsePunto2DTO(estacion, distanciaMasLejana);
        return Optional.of(estacionResponsePunto2DTO);
    }

    public Optional<Estacion> findEstacionCercanaByUbicacion(double lat, double lon) {
        List<Estacion> estaciones = estacionRepository.findAll();
        Estacion estacionMasCercana = null;
        double distanciaMasCorta = Double.MAX_VALUE;

        for (Estacion estacion : estaciones) {
            double distancia = euclideanDistance(
                    lat, lon, estacion.getLatitud(), estacion.getLongitud());

            if (distancia < distanciaMasCorta) {
                distanciaMasCorta = distancia;
                estacionMasCercana = estacion;
            }
        }

        return Optional.ofNullable(estacionMasCercana);
//esto es equivalente a lo anterior
//        return findAll().stream()
//                .min((estacion1, estacion2) -> {
//                    double distancia1 = calcularDistancia(
//                            estacion1.getLatitud(), estacion1.getLongitud(), lat, lon);
//                    double distancia2 = calcularDistancia(
//                            estacion2.getLatitud(), estacion2.getLongitud(), lat, lon);
//                    return Double.compare(distancia1, distancia2);
//                });

    }

    public Optional<List<EstacionResponsePunto3DTO>> findEstacionPorDistancia(
            Integer id, double distanciaRequired) {
        Optional<Estacion> estacion = estacionRepository.findById(id);

        List<Estacion> estacions = estacionRepository.findAll();
//        Estacion estacionMasCercana = null;

        List<EstacionResponsePunto3DTO> estacionResponsePunto3DTOS = new ArrayList<>();
        EstacionResponsePunto3DTO estacionResponsePunto3DTOPrimera = new EstacionResponsePunto3DTO(0, estacion.get(), 0);
        estacionResponsePunto3DTOS.add(estacionResponsePunto3DTOPrimera);

        int contDistancia = 0;
        int distanciaTotal = 0;
        int i = id - 1;

        while (i < estacions.size() - 1 && contDistancia < distanciaRequired) {
            Estacion estacion1 = estacions.get(i);
            Estacion estacion2 = estacions.get(i + 1);

            double distancia = euclideanDistance(
                    estacion1.getLatitud(), estacion1.getLongitud()
                    , estacion2.getLatitud(), estacion2.getLongitud());

//            if (distancia < distanciaMasCercana) {
//                distanciaMasCercana = distancia;
//                estacionMasCerca1 = estacion1;
//                estacionMasCerca2 = estacion2;
//            }

//            for (Estacion estacion : estaciones) {
//                double distancia = euclideanDistance(lat, lon, estacion.getLatitud(), estacion.getLongitud());
//
//                if (distancia < distanciaMasCorta) {
//                    distanciaMasCorta = distancia;
//                    estacionMasCercana = estacion;
//                }
//            }

            contDistancia += distancia;
            EstacionResponsePunto3DTO estacionResponsePunto3DTO = new EstacionResponsePunto3DTO(contDistancia, estacion2, distancia);
            estacionResponsePunto3DTOS.add(estacionResponsePunto3DTO);
            distanciaTotal += contDistancia;

            if (distanciaTotal > distanciaRequired) {
                break;
            }
            i++;
        }

//        Estacion estacion;
//        for (Estacion estacion : estacionList) {
//            estacion = new Estacion(estacionId, playlist, estacion);
//            estacionRepository.saveAndFlush(estacion);
//        }

//        estacionRepository.save(estacion);
//        EstacionResponsePunto2DTO estacionResponsePunto2DTO=
//                new EstacionResponsePunto2DTO(estacion,distanciaMasLejana);
        return Optional.of(estacionResponsePunto3DTOS);
    }

    private double euclideanDistance(double lat1, double lon1, double lat2, double lon2) {
        // Distancia euclidiana con cada grado correspondiente a 110,000 metros
        double degreesToMeters = 110000.0;

        double dLat = (lat2 - lat1) * degreesToMeters;
        double dLon = (lon2 - lon1) * degreesToMeters;

        return Math.sqrt(dLat * dLat + dLon * dLon);
    }

    public boolean deleteById(Integer estacionId) {
        if (!estacionRepository.existsById(estacionId)) {
            return false;
        }
        estacionRepository.deleteById(estacionId);
        return true;
    }

    public EstacionResponse update(Integer id, EstacionRequest estacionRequest) {
        Estacion estacion = estacionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Estacion [%d] inexistente", id))
        );
        Estacion estacion1 = estacion.update(estacionRequest.toEstacion());
        estacionRepository.save(estacion1);

        return new EstacionResponse(estacion1);
    }

    public List<EstacionResponse> Punto1(String name/*, Integer artistId*/) {

        name = '%' + name + '%';
        List<Estacion> estacionList =
                estacionRepository.Punto1(name/*, artistId*/);

        List<EstacionResponse> estacionResponseGetPunto1DTOS = estacionList.stream()
                .map(estacion -> new EstacionResponse(estacion))
                .toList();
        return estacionResponseGetPunto1DTOS;
    }

    public Optional<Estacion> addPuntoA(EstacionRequest estacionRequest) {

        double distancia = getDistanciaEstacionCercanaByUbicacion(
                estacionRequest.getLatitud(),
                estacionRequest.getLongitud()
        );
        if (distancia == 500) {
            Estacion estacion = new Estacion(
                    estacionRequest.getNombre(),
                    estacionRequest.getLatitud(),
                    estacionRequest.getLongitud()
            );
            return Optional.of(estacionRepository.save(estacion));
        } else {
//            return Optional.ofNullable(new Estacion());
            return Optional.empty();
        }
    }
//        List<Estacion> estacionList=new ArrayList<>();
//        int contSeconds = 0;
//        int i = 0;
//        Estacion estacionMasLejana1 = null;
//        Estacion estacionMasLejana2 = null;
//        double distanciaMasLejana = Double.MIN_VALUE;
//
//
//        // Agregar estacions hasta alcanzar la cantidad de playlistSeconds
//        while (i < estacions.size()-1 /*&& contSeconds < playlistSeconds*/) {
//            Estacion estacion1 = estacions.get(i);
//            Estacion estacion2 = estacions.get(i+1);
//
//            double distancia = euclideanDistance(
//                    estacion1.getLatitud(), estacion1.getLongitud()
//                    , estacion2.getLatitud(), estacion2.getLongitud());
//
//            if (distancia > distanciaMasLejana) {
//                distanciaMasLejana = distancia;
//                estacionMasLejana1 = estacion1;
//                estacionMasLejana2 = estacion2;
//            }
//
//            i++;
//        }
//        Estacion estacion = new Estacion("Estacion media",
//                (estacionMasLejana1.getLatitud()+estacionMasLejana2.getLatitud())/2,
//                (estacionMasLejana2.getLongitud()+estacionMasLejana2.getLongitud())/2
//        );
//
//        estacionRepository.save(estacion);
//        EstacionResponsePunto2DTO estacionResponsePunto2DTO=
//                new EstacionResponsePunto2DTO(estacion,distanciaMasLejana);
//        return Optional.of(estacionResponsePunto2DTO);

    public double getDistanciaEstacionCercanaByUbicacion(double lat, double lon) {
        List<Estacion> estaciones = estacionRepository.findAll();
        Estacion estacionMasCercana = null;
        double distanciaMasCorta = 500; //Double.MAX_VALUE;

        for (Estacion estacion : estaciones) {
            double distancia = euclideanDistance(
                    lat, lon, estacion.getLatitud(), estacion.getLongitud());

            if (distancia < distanciaMasCorta) {
                distanciaMasCorta = distancia;
                estacionMasCercana = estacion;
            }
        }
        return distanciaMasCorta;
    }

    public List<Estacion> updatePuntoB(Integer id, EstacionRequest estacionRequest) {
//        Optional<Estacion> estacion = estacionRepository.findById(id);
        Estacion estacion = estacionRepository.findById(id)
        .orElseThrow(
                () -> new ResourceNotFoundException(String.format("Estacion [%d] inexistente", id))
        );
        List<Estacion> estacionList =new ArrayList<>();

       double distancia = getDistanciaEstacionCercanaByUbicacion(
                estacionRequest.getLatitud(),
                estacionRequest.getLongitud()
        );

        if (distancia == 500) {
            Estacion estacion1=new Estacion(estacion);
            estacionList.add(estacion1);
            estacion.update(estacionRequest.toEstacion());
            estacionList.add(estacion);
            estacionRepository.save(estacion);
//            estacionList.add(estacion.get());
        }

        return estacionList;
    }


}
