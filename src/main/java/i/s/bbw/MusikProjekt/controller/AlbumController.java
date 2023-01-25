package i.s.bbw.MusikProjekt.controller;


import i.s.bbw.MusikProjekt.model.Album;
import i.s.bbw.MusikProjekt.repository.AlbumRepository;
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
@RequestMapping("/api/v1/albums")
public class AlbumController {
    @Autowired
    private AlbumRepository albumRepository;
    @GetMapping
    public List<Album> getAllAlbums(){
        return albumRepository.findAll();
    }
    // build create Role REST API
    @PostMapping
    public Album createAlbum(@RequestBody Album album) {
        return albumRepository.save(album);
    }
    @GetMapping("{id}")
    public ResponseEntity<Album> getAlbumById(@PathVariable  long id){
        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Album existiert nicht mit id:" + id));
        return ResponseEntity.ok(album);
    }
    @PutMapping("{id}")
    public ResponseEntity<Album> updateAlbum(@PathVariable long id,@RequestBody Album albumDetails) {
        Album updateAlbum = albumRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Album existiert nicht mit id: " + id));
        updateAlbum.setName(albumDetails.getName());
        updateAlbum.setAnzahlsongs(albumDetails.getAnzahlsongs());
        Album updatedAlbum = albumRepository.save(updateAlbum);
        return ResponseEntity.ok(updatedAlbum);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteAlbum(@PathVariable long id){
        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Album existiert nicht mit id: " + id));
        albumRepository.delete(album);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}