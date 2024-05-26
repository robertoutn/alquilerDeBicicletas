package ar.edu.utn.frc.bda.estaciones.web.services;

import ar.edu.utn.frc.bda.estaciones.domain.Estacion;
import ar.edu.utn.frc.bda.estaciones.repositories.EstacionRepository;
import ar.edu.utn.frc.bda.estaciones.web.api.dto.EstacionRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
class EstacionServiceTest {

    @InjectMocks
    private EstacionService estacionService;

    @Mock
    private EstacionRepository estacionRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
//     estacionRepository=mock(EstacionRepository.class);
//     estacionService=new EstacionService(estacionRepository);

}
   @Test
    void findAll() {
           Estacion estacion1 = new Estacion();
        estacion1.setId(50);
        estacion1.setNombre("Arguello");
        estacion1.setLatitud(-31.452961123175);
        estacion1.setLongitud(-64.2540911211195);

        Estacion estacion2 = new Estacion();
        estacion2.setId(51);
        estacion2.setNombre("Villa Allende");
        estacion2.setLatitud(-31.462961123175);
        estacion2.setLongitud(-64.2640911211195);

        List<Estacion> estacionList = new ArrayList<>();
        estacionList.add(estacion1);
        estacionList.add(estacion2);

        when(estacionRepository.findAll()).thenReturn(estacionList);

        List<Estacion> resultado = estacionService.findAll();

        assertEquals(estacionList.size(), resultado.size());}

    @Test
    void getEstacionById() {
        Estacion estacion = new Estacion();
        estacion.setId(50);
        estacion.setNombre("Arguello");
        estacion.setLatitud(-31.452961123175);
        estacion.setLongitud(-64.2540911211195);

        when(estacionRepository.findById(50)).thenReturn(Optional.of(estacion));

        Optional<Estacion> resultado = estacionService.getEstacionById(50);

        assertEquals(estacion.getId(), resultado.get().getId());
        assertEquals(estacion.getNombre(), resultado.get().getNombre());
    }

    @Test
    void addEstacion() {
        Estacion estacion = new Estacion();
        estacion.setId(50);
        estacion.setNombre("Arguello");
        estacion.setLatitud(-31.452961123175);
        estacion.setLongitud(-64.2540911211195);

        Optional<Estacion> estacionOptional=Optional.of(estacion);

        EstacionRequest estacionRequest=new EstacionRequest("Arguello",-31.452961123175,-64.2540911211195);

        when(estacionRepository.save(any(Estacion.class))).thenReturn(estacion);

        Optional<Estacion> estacionAgregada = estacionService.addEstacion(estacionRequest);

        assertEquals(estacionOptional, estacionAgregada);
    }

    @Test
    void findEstacionCercanaByUbicacion() {
        Estacion estacion1 = new Estacion();
        estacion1.setId(50);
        estacion1.setNombre("Casa");
        estacion1.setLatitud(-31.452961123175);
        estacion1.setLongitud(-64.2540911211195);

        Estacion estacion2 = new Estacion();
        estacion2.setId(51);
        estacion2.setNombre("Casa2");
        estacion2.setLatitud(-31.482961123175);
        estacion2.setLongitud(-64.2840911211195);

        Estacion estacion3 = new Estacion();
        estacion3.setId(52);
        estacion3.setNombre("Casa3");
        estacion3.setLatitud(-31.522961123175);
        estacion3.setLongitud(-64.3240911211195);

        List<Estacion> estacionList = new ArrayList<>(Arrays.asList(estacion1, estacion2, estacion3));
        when(estacionRepository.findAll()).thenReturn(estacionList);

        double latitud = -31.482961120000;
        double longitud = -64.2840911210000;

        Optional<Estacion> Estacionresultado = estacionService.findEstacionCercanaByUbicacion(latitud, longitud);

        Optional<Estacion> estacionOptional=Optional.of(estacion2);

        assertEquals(estacionOptional, Estacionresultado);
        }
}