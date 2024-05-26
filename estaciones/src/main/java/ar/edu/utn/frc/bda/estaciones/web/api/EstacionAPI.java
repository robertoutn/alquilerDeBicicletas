package ar.edu.utn.frc.bda.estaciones.web.api;

import ar.edu.utn.frc.bda.estaciones.domain.Estacion;
import ar.edu.utn.frc.bda.estaciones.web.api.dto.EstacionRequest;
//import ar.edu.utn.frc.bda.estaciones.web.api.dto.EstacionResponse;
import ar.edu.utn.frc.bda.estaciones.web.api.dto.EstacionResponse;
import ar.edu.utn.frc.bda.estaciones.web.api.dto.EstacionResponsePunto2DTO;
import ar.edu.utn.frc.bda.estaciones.web.api.dto.EstacionResponsePunto3DTO;
import ar.edu.utn.frc.bda.estaciones.web.services.EstacionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/estacion")
public class EstacionAPI {
    private final EstacionService estacionService;
    public EstacionAPI(EstacionService estacionService) {
        this.estacionService = estacionService;
    }

    @GetMapping
    public ResponseEntity<List<Estacion>> findAll() {
        List<Estacion> estacions = estacionService.findAll();

        return estacions.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(estacions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getEstacionById(
            @PathVariable (name = "id", required = false) Integer id) {
            Optional<Estacion> estacion = estacionService.getEstacionById(id);
            if (estacion.isPresent()) {
                return ResponseHandler.success(estacion);
//                return new ResponseEntity<>(estacion, HttpStatus.ACCEPTED);
            } else {
                return ResponseHandler.notFound("Estacion inexistente");
            }
//            if (estacion.isPresent()) {
//                return ResponseEntity.ok(estacion);
//            } else {
//                return ResponseEntity.notFound().build();
//            }
    }

    @GetMapping("/findEstacionCercanaByUbicacion")
    public ResponseEntity<Object> findEstacionCercanaByUbicacion(
            @RequestParam (name = "latitud") double lat,
            @RequestParam (name = "longitud") double lon
    ) {
        Optional<Estacion> estacion = estacionService.findEstacionCercanaByUbicacion(lat,lon);
        return estacion.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(estacion);
    }

    @GetMapping("/findEstacionPorDistancia")
    public ResponseEntity<Optional<List<EstacionResponsePunto3DTO>>> findEstacionPorDistancia(
            @RequestParam (name = "estacionId") Integer estacionId,
            @RequestParam (name = "distanciaRequerida") double distanciaRequerida
    ) {
        Optional<List<EstacionResponsePunto3DTO>> estacion = estacionService.findEstacionPorDistancia(estacionId,distanciaRequerida);
        return estacion.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(estacion);
    }

    @GetMapping("/finByName")
    public ResponseEntity<List<EstacionResponse>> finByName(
            @RequestParam (name = "customerId") String name
//            ,@RequestParam (name = "artistId") Integer artistId
    )
    {
        List<EstacionResponse> estacionResponseGetPunto1DTOList = estacionService.Punto1(name/*, artistId*/);
        return estacionResponseGetPunto1DTOList.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(estacionResponseGetPunto1DTOList);
    }

    @PostMapping
    public ResponseEntity<Object> addEstacion(@Valid @RequestBody EstacionRequest estacionRequest) {
        Optional<Estacion> estacion=estacionService.addEstacion(estacionRequest);
        return estacion.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(estacion);
    }

    @PostMapping("/crearEstacionMedia")
    public ResponseEntity<EstacionResponsePunto2DTO> punto2(/*@Valid @RequestBody EstacionRequest estacionRequest*/
        @RequestParam (name = "fecha") LocalDateTime fecha//2023-10-01T01:00:00
        ,@RequestParam (name = "lat") double lat//-31.4
        ) {
        Optional<EstacionResponsePunto2DTO> estacion=estacionService.add(fecha,lat);
        return estacion.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(estacion.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Integer id) {
        return estacionService.deleteById(id)
            ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(
            @PathVariable Integer id,
            @Valid @RequestBody EstacionRequest request) {
        EstacionResponse estacionResponse = estacionService.update(
                id, request);

        return new ResponseEntity<>(estacionResponse, HttpStatus.ACCEPTED);
    }

//    En la creación de una nueva Estación, dados los datos necesarios para la creación de esta
//            (URL sugerida: POST:/api/estaciones) con la estructura de una estación en el body del request. En
//    lugar de simplemente crear la estación realizar el control en el momento de la creación que no
//    exista ya ninguna otra estación que esté a menos de 500 mts de distancia de esta nueva que se
//    está intentando crear
//    El resultado debe responder con la estructura de la nueva estación creada con el id asignado a la
//    misma en caso de que la creación haya sido exitosa. Además, debe retornar el código 200 o 201
//    si la operación fue correcta o 204 en caso de que ya existiese al menos una estación a menos de
//500 mts de la estación que se intentó crear
    @PostMapping("/PuntoA")
    public ResponseEntity<Object> PuntoA(@Valid @RequestBody EstacionRequest estacionRequest) {
        Optional<Estacion> estacion=estacionService.addPuntoA(estacionRequest);
        return estacion.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(estacion);
//         "latitud": -31.44,
//  "longitud": -64.19 <500
    }

//    Permitir la modificación de una estación determinada en base a los dtos suministrados en el
//    body (URL sugerida PATCH:/api/estaciones/{idEstacion}) con el body conteniendo la estructura
//    de la estación. En la modificación se puede modificar solo el nombre o la latitud y/o longitud y en
//    cuyo caso serán necesario controlar también el radio de 500 mts especificado en el punto
//    anterior y en caso de no cumplirse la condición no realizar la modificación.
//    El resultado debe informar en el caso de haber resultado una modificación exitosa, la versión
//    previa a la modificación y la versión modificada de la Estación con el código 200 y en el caso de
//    que no se hubiera podido realizar la modificación por que no existe la estación código 404 o si
//    fuere porque no se cumple la condición, código 206, 406 u otro que el alumno defina.
    @PatchMapping("/{id}")
    public ResponseEntity<List<Estacion>> puntoB(
            @PathVariable Integer id,
            @Valid @RequestBody EstacionRequest request) {
        List<Estacion>  estacionResponse = estacionService.updatePuntoB(
                id, request);

        return new ResponseEntity<>(estacionResponse, HttpStatus.ACCEPTED);
    }






}
