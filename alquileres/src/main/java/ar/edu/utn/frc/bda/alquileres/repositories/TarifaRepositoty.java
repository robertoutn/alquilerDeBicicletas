package ar.edu.utn.frc.bda.alquileres.repositories;

import ar.edu.utn.frc.bda.alquileres.domain.Tarifa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TarifaRepositoty extends JpaRepository <Tarifa, Integer> {
    Tarifa getTarifaByDiaSemana(int diaSemana);
    Optional<Tarifa> getTarifaByDiaMesAndMesAndAnio(int diaMes, int mes, int anio);
}
