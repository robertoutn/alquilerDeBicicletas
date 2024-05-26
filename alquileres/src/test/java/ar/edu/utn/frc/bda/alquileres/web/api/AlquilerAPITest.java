package ar.edu.utn.frc.bda.alquileres.web.api;

//import ar.edu.utn.frc.bda.alquileres.adapters.EstacionService;
import ar.edu.utn.frc.bda.alquileres.domain.Alquiler;
import ar.edu.utn.frc.bda.alquileres.repositories.AlquilerRepository;
import ar.edu.utn.frc.bda.alquileres.web.api.dto.AlquilerFiltroRequest;
import ar.edu.utn.frc.bda.alquileres.web.services.AlquilerService;
import ar.edu.utn.frc.bda.alquileres.web.services.EstacionService;
import ar.edu.utn.frc.bda.alquileres.web.services.MonedaService;
import ar.edu.utn.frc.bda.alquileres.web.services.TarifaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AlquilerAPITest {

    private AlquilerAPI alquilerAPI;
    private MonedaService monedaService;
    private AlquilerRepository alquilerRepository;
    private TarifaService tarifaService;
    private EstacionService estacionService;
    private AlquilerService alquilerService;
    private final AlquilerFiltroRequest alquilerFiltroRequest=new AlquilerFiltroRequest(
            "1",2,3,4);
    private List<Alquiler> alquilerList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        tarifaService=mock(TarifaService.class);
        alquilerRepository=mock(AlquilerRepository.class);
        alquilerService=mock(AlquilerService.class);
        alquilerService=new AlquilerService(alquilerRepository,tarifaService,estacionService);
    alquilerAPI=new AlquilerAPI(alquilerService,monedaService);
    }
  
    @Test
    void getAlquilerWithValidData() {
        when(alquilerService.getAlquilerConFiltro("1", 2,3,4))
                .thenReturn(alquilerList);

        ResponseEntity<Object> responseEntity = alquilerAPI.getAlquilerConFiltro(alquilerFiltroRequest);

        assertNotEquals(HttpStatus.OK, responseEntity.getStatusCode(),"No pasó test happy path");
//        assertNull(responseEntity.getBody());
    }


}