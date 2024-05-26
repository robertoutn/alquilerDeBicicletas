package ar.edu.utn.frc.bda.estaciones.repositories;

import ar.edu.utn.frc.bda.estaciones.domain.Estacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EstacionRepository extends JpaRepository<Estacion,Integer> {

    @Query(value = "select t.* from ESTACIONES t\n"
            +
//            "    join main.albums a on a.AlbumId = t.AlbumId\n" +
//            "    join main.invoice_items ii on t.AlquilerId = ii.AlquilerId\n" +
//            "    join main.invoices i on i.InvoiceId = ii.InvoiceId\n" +
            " where 1 = 1 " +
            " and (:name IS NULL OR t.NOMBRE like :name) " +
//            " and (:artistId   IS NULL OR a.ArtistId = :artistId) "
//            +
            " order by t.NOMBRE"
            , nativeQuery = true
    )
    List<Estacion> Punto1(
            @Param("name") String name
//            ,@Param("artistId") Integer artistId
    );

    @Query(value = "select * from ESTACIONES t\n" +
            "    where t.FECHA_HORA_CREACION > :fecha\n" +
            "    and t.LATITUD> :lat\n" +
            "order by t.FECHA_HORA_CREACION\n" +
            "limit 4"
            , nativeQuery = true
    )
    List<Estacion> Punto2(
            @Param("fecha") LocalDateTime fecha
            ,@Param("lat") double lat
    );



}
