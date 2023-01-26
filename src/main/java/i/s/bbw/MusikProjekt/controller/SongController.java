package i.s.bbw.MusikProjekt.controller;


import i.s.bbw.MusikProjekt.model.Song;
import i.s.bbw.MusikProjekt.repository.SongRepository;
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
@RequestMapping("/api/v1/song")
public class SongController {
    @Autowired
    private SongRepository songRepository;
    @GetMapping
    public List<Song> getAllSong(){
        return songRepository.findAll();
    }
    // build create Role REST API
    @PostMapping
    public Song createSong(@RequestBody Song song) {
        return songRepository.save(song);
    }
    @GetMapping("{id}")
    public ResponseEntity<Song> getSongById(@PathVariable  long id){
        Song song = songRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Song existiert nicht mit id:" + id));
        return ResponseEntity.ok(song);
    }
    @PutMapping("{id}")
    public ResponseEntity<Song> updateSong(@PathVariable long id,@RequestBody Song songDetails) {
        Song updateSong = songRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Song existiert nicht mit id: " + id));
        updateSong.setName(songDetails.getName());
        updateSong.setDauer(songDetails.getDauer());
        Song updatedSong = songRepository.save(updateSong);
        return ResponseEntity.ok(updateSong);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteSong(@PathVariable long id){
        Song song = songRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Song existiert nicht mit id: " + id));
        songRepository.delete(song);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}