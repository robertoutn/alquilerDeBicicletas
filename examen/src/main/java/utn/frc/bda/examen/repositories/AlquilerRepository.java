package utn.frc.bda.examen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utn.frc.bda.examen.domain.Alquiler;

import java.util.List;

@Repository
public interface AlquilerRepository extends JpaRepository<Alquiler, Integer> {
//    List<Alquiler> findAllGenreGenreIdAndArtistArtistId(Integer alquilerId, Integer genreId);
    @Query(value = "SELECT t.* FROM alquileres t "
//            +
//            "join main.albums a on a.AlbumId = t.AlbumId " +
//            "WHERE t.genreId = :genreId " +
//            "   AND a.artistId = :artistId " +
//            "order by AlbumId, AlquilerId"
            , nativeQuery = true
    )
    List<Alquiler> findAllByGenreIdAndArtistId(Integer genreId, Integer artistId);

//Para ello deberá buscar todos los alquilers que estén relacionados a través de un invoice_item con
//un invoice del cliente enviado como parámetro y
//        filtrarlos por el conjunto de álbumes asociados al artista suministrado.
    @Query(value = "select t.* from alquileres t\n"
//            +
//            "    join main.albums a on a.AlbumId = t.AlbumId\n" +
//            "    join main.invoice_items ii on t.AlquilerId = ii.AlquilerId\n" +
//            "    join main.invoices i on i.InvoiceId = ii.InvoiceId\n" +
//            " where 1 = 1 " +
//            " and (:customerId IS NULL OR i.CustomerId = :customerId) " +
//            " and (:artistId   IS NULL OR a.ArtistId = :artistId) " +
//            " order by AlbumId, AlquilerId"
            , nativeQuery = true
    )
    List<Alquiler> Punto1(
            @Param("customerId") Integer customerId
            ,@Param("artistId") Integer artistId
    );

//    List<Alquiler> findAllByAlbum_AlbumId (Integer albumId);

}