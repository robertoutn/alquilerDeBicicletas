package ar.edu.utn.frc.bda.alquileres.repositories;

import ar.edu.utn.frc.bda.alquileres.domain.Alquiler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlquilerRepository extends JpaRepository<Alquiler, Integer> {
    @Query("SELECT t FROM ALQUILERES t WHERE 1 = 1"
            + " AND (:idCliente IS NULL OR t.idCliente = :idCliente)"
            + " AND (:estado IS NULL OR t.estado = :estado)"
            + " AND (:estacionRetiro IS NULL OR t.estacionRetiro = :estacionRetiro)"
            + " AND (:estacionDevolucion IS NULL OR t.estacionDevolucion = :estacionDevolucion)"
    )
    List<Alquiler> findByParameters(
                @Param("idCliente") String idCliente
                ,@Param("estado") Integer estado
                ,@Param("estacionRetiro") Integer estacionRetiro
                ,@Param("estacionDevolucion") Integer estacionDevolucion
                                );

}
