package i.s.bbw.MusikProjekt.controller;


import i.s.bbw.MusikProjekt.model.Artist;
import i.s.bbw.MusikProjekt.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/artist")
public class ArtistController {
    @Autowired
    private ArtistRepository artistRepository;
    @GetMapping
    public List<Artist> getAllArtist(){
        return artistRepository.findAll();
    }
    // build create Role REST API
    @PostMapping
    public Artist createArtist(@RequestBody Artist artist) {
        return artistRepository.save(artist);
    }
    @GetMapping("{id}")
    public ResponseEntity<Artist> getArtistById(@PathVariable  long id){
        Artist artist = artistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Artist existiert nicht mit id:" + id));
        return ResponseEntity.ok(artist);
    }
    @PutMapping("{id}")
    public ResponseEntity<Artist> updateArtist(@PathVariable long id,@RequestBody Artist artistDetails) {
        Artist updateArtist = artistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Artist existiert nicht mit id: " + id));
        updateArtist.setName(artistDetails.getName());
        updateArtist.setVorname(artistDetails.getVorname());
        updateArtist.setAlter(artistDetails.getAlter());
        Artist updatedArtist = artistRepository.save(updateArtist);
        return ResponseEntity.ok(updateArtist);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteArtist(@PathVariable long id){
        Artist artist = artistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Artist existiert nicht mit id: " + id));
        artistRepository.delete(artist);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}