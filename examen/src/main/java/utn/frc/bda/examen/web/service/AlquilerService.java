package utn.frc.bda.examen.web.service;

import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utn.frc.bda.examen.domain.Alquiler;
import utn.frc.bda.examen.repositories.AlquilerRepository;
import utn.frc.bda.examen.web.service.api.dto.AlquilerResponseGetPunto3;
import utn.frc.bda.examen.web.service.api.dto.AlquilerResponseGetPunto3DTO;
import utn.frc.bda.examen.web.service.api.dto.AlquilerResponseGetPunto1DTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlquilerService {
    private final AlquilerRepository alquilerRepository;

    public AlquilerService(AlquilerRepository alquilerRepository) {
        this.alquilerRepository = alquilerRepository;
    }
//    private final IdentifierRepository identifierRepository;

    public List<Alquiler> findAll() {
        return alquilerRepository.findAll();
    }

    public Optional<Alquiler> findById(Integer id) {
        return alquilerRepository.findById(id);
    }

    public boolean deleteById(Integer id) {
        if (!alquilerRepository.existsById(id)) {
            return false;
        }
        alquilerRepository.deleteById(id);
        return true;
    }

    @Transactional
    public Alquiler create(Integer id,
                        String clienteId
    ) {

        Alquiler alquiler = alquilerRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("Alquiler no existente");
        });


//        val alquilerId = identifierRepository.nextValue(Alquiler.TABLE_NAME);
//        val playlists = new ArrayList<Playlist>();
//
//        val alquiler = new Alquiler(alquilerId,
//                name,
//                alquiler,
//                mediaType,
//                genre,
//                composer,
//                milliseconds,
//                bytes,
//                unitPrice,play);

        return alquilerRepository.save(alquiler);
    }

//    @Transactional
//    public Alquiler update(Integer id, Integer estado, String artistName) {
//        val alquiler = alquilerRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Alquiler not Found"));
//        alquiler.update(estado);
//        alquilerRepository.save(alquiler);
//        return alquiler;
//    }

    public List<Alquiler> findAllGenreGenreIdAndArtistArtistId(Integer genreId, Integer artistId) {
        return alquilerRepository.findAllByGenreIdAndArtistId(genreId, artistId);
    }


    public List<AlquilerResponseGetPunto1DTO> Punto1(Integer customerId, Integer artistId) {

        List<Alquiler> alquilerList =
                alquilerRepository.Punto1(customerId, artistId);

        List<AlquilerResponseGetPunto1DTO> alquilerResponseGetPunto1DTOS =alquilerList.stream()
                .map(alquiler -> new AlquilerResponseGetPunto1DTO(alquiler))
                .toList();
        return alquilerResponseGetPunto1DTOS;
    }

    public List<Alquiler> Punto2(Integer customerId, Integer artistId) {

        List<Alquiler> alquilerList =
                alquilerRepository.Punto1(customerId, artistId);

            return alquilerList;
    }

    public AlquilerResponseGetPunto3DTO findAllByAlquiler_AlquilerId(Integer alquilerId) {

//        List<Alquiler> alquilerList =
//                alquilerRepository.findAllByAlquiler_AlquilerId(alquilerId);
        List<AlquilerResponseGetPunto3> alquilerResponseGetPunto3List =
                new ArrayList<>();
//        Optional<Alquiler> alquiler=alquilerService.findById(alquilerId);
        AlquilerResponseGetPunto3DTO response = new AlquilerResponseGetPunto3DTO();
//        response.setAlquilerTiltle(alquiler.get().getTitle());
//        response.setAlquilerResponseGetPunto3List(
//                alquilerList.stream()
//                    .map(t-> new AlquilerResponseGetPunto3(t))
//                    .toList()
//                );
//        for (Alquiler t :alquilerList
//                ) {
//            AlquilerResponseGetPunto3 alquilerResponse =new AlquilerResponseGetPunto3();
//            alquilerResponse.setName(t.getName());
//            alquilerResponse.setMediaType(t.getMediaType());
//            alquilerResponse.setGenre(t.getGenre());
//            alquilerResponse.setComposer(t.getComposer());
//            alquilerResponse.setMilliseconds(t.getMilliseconds());
//            alquilerResponse.setBytes(t.getBytes());
//            alquilerResponseGetPunto3List.add(alquilerResponse);
//        }
//        response.setAlquilerResponseGetPunto3List(alquilerResponseGetPunto3List);
            return response;
    }




}