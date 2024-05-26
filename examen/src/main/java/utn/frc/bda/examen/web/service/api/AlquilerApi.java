package utn.frc.bda.examen.web.service.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.frc.bda.examen.domain.Alquiler;
import utn.frc.bda.examen.web.service.AlquilerService;
import utn.frc.bda.examen.web.service.api.dto.AlquilerResponseGetPunto1DTO;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/alquilers")
public class AlquilerApi {
    private final AlquilerService alquilerService;

    public AlquilerApi(AlquilerService alquilerService) {
        this.alquilerService = alquilerService;
    }

    @GetMapping
    public ResponseEntity<List<AlquilerResponseGetPunto1DTO>> findAll() {
        List<AlquilerResponseGetPunto1DTO> list = alquilerService.findAll().stream()
                .map(p->new AlquilerResponseGetPunto1DTO(p))
                .toList();

        return list.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(list);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Alquiler> findById(
            @PathVariable Integer id) {
        Optional<Alquiler> optionalCustomer =
                alquilerService.findById(id);

        return optionalCustomer.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(optionalCustomer.get());
    }

//    @GetMapping("/albumId/{albumId}")
//    public ResponseEntity<AlquilerResponseGetPunto3DTO> findAllByAlbum_AlbumId(
//            @PathVariable Integer albumId) {
//
//            Optional<Album> albumOptional = albumService.findById(albumId);
//            if(albumOptional.isEmpty()){
//                return ResponseEntity.notFound().build();
//            }
//
//        AlquilerResponseGetPunto3DTO response =
//                alquilerService.findAllByAlbum_AlbumId(albumId);
//
//        return response == null
//                ? ResponseEntity.notFound().build()
//                : ResponseEntity.ok(response);
//    }

//    @PostMapping
//    public ResponseEntity<Object> create(@RequestBody CreateAlquilerRequest aRequest) {
//        try {
//            val alquiler = alquilerService.create(
//                    aRequest.getName(),
//                    aRequest.getAlbumName(),
//                    aRequest.getMediaTypeName(),
//                    aRequest.getGenreName(),
//                    aRequest.getComposer(),
//                    aRequest.getMilliseconds(),
//                    aRequest.getBytes(),
//                    aRequest.getUnitPrice());
//
//            return ResponseEntity.status(HttpStatus.CREATED).body(alquiler);
//        } catch (IllegalArgumentException e) {
//            return ResponseHandler.badRequest(e.getMessage());
//        } catch (Exception e) {
//            return ResponseHandler.internalError();
//        }
//    }

//    @PatchMapping("/{id}")
//    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody CreateAlquilerRequest aRequest) {
//        try {
//            alquilerService.update(id,
//                    aRequest.getName(),
//                    aRequest.getAlbumName(),
//                    aRequest.getMediaTypeName(),
//                    aRequest.getGenreName(),
//                    aRequest.getComposer(),
//                    aRequest.getMilliseconds(),
//                    aRequest.getBytes(),
//                    aRequest.getUnitPrice());
//            return ResponseHandler.noContent();
//        } catch (IllegalArgumentException e) {
//            return ResponseHandler.badRequest(e.getMessage());
//        } catch (Exception e) {
//            return ResponseHandler.internalError();
//        }
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        return alquilerService.deleteById(id)
            ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

//    @PostMapping
//    public ResponseEntity<AlquilerResponseGetPunto1DTO> create(
//            @RequestParam (name = "idEstacionRetiro") Integer idEstacionRetiro,
//            @RequestParam (name = "idCliente") String idCliente
//    ) {
//
//        Optional<Alquiler> alquiler = alquilerService.create(
//                idEstacionRetiro, idCliente
//        );
//
//        return alquiler.isEmpty()
//                ? ResponseEntity.notFound().build()
//                : ResponseEntity.ok(new AlquilerResponseGetPunto1DTO(
//                alquiler.get().getIdCliente(),
//                alquiler.get().getEstado()
//        ));
//    }

//    @GetMapping("/")
//    public ResponseEntity<Object> getAlquilersByArtistIdAndGenreId(@RequestParam Integer artistid, @RequestParam Integer genreid) {
//        try {
//            // 404 en el caso que no exista el id de artista.
//            val artist = artistService.findById(artistid);
//            if(artist.isEmpty()){
//                return ResponseEntity.notFound().build();
//            }
//
//            val responses = alquilerService.findAllGenreGenreIdAndArtistArtistId(genreid, artistid)
//                    .stream()
//                    .map(AlquilerByGenreAndArtistResponse::from)
//                    .toList();
//
//            if (responses.isEmpty()) {
//                // 204 si el id de artista existe, pero no hay ningún alquiler que
//                // coincida con el filtro de artista/álbum
//                return ResponseEntity.noContent().build();
//            }
//            //  200 si responde correctamente
//            return ResponseHandler.success(responses);
//        } catch (Exception e) {
//            return ResponseEntity.notFound().build();
//        }
//    }

    //Obtener todos los Alquilers (Pistas o Canciones) dados un cliente (Id de customer) y un artista (id de
//artista)
//(URL sugerida: GET:/api/alquilers?customerid=<id de cliente>&artistid=<id de artista>).
    @GetMapping("/con2Parametros")
    public ResponseEntity<List<AlquilerResponseGetPunto1DTO>> Punto1(
        @RequestParam (name = "customerId") Integer customerId,
        @RequestParam (name = "artistId") Integer artistId)
    {

////        404 en el caso que no exista el id de cliente.
//        Optional<Customer> customerOptional = customerService.findById(customerId);
//        if (customerOptional.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
////        204 si el id de cliente y el artista existen, pero el cliente no posee ningún alquiler del artista y
//        Optional<Artist> artistOptional = artistService.findById(artistId);
        List<AlquilerResponseGetPunto1DTO> alquilerResponseGetPunto1DTOList = alquilerService.Punto1(customerId, artistId);
//        if (artistOptional.isEmpty() && alquilerResponseGetPunto1DTOList.isEmpty() ) {
//            return ResponseEntity.noContent().build();
//        }
//        Se espera que la respuesta tenga código 200 si responde correctamente,
        return ResponseEntity.ok(alquilerResponseGetPunto1DTOList);
    }




}
