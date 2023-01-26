package i.s.bbw.MusikProjekt.controller;


import i.s.bbw.MusikProjekt.model.Genres;
import i.s.bbw.MusikProjekt.repository.GenresRepository;
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
@RequestMapping("/api/v1/genres")
public class GenresController {
    @Autowired
    private GenresRepository genresRepository;
    @GetMapping
    public List<Genres> getAllGenres(){
        return genresRepository.findAll();
    }
    // build create Role REST API
    @PostMapping
    public Genres createGenres(@RequestBody Genres genres) {
        return genresRepository.save(genres);
    }
    @GetMapping("{id}")
    public ResponseEntity<Genres> getGenresById(@PathVariable  long id){
        Genres genres = genresRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Genres existiert nicht mit id:" + id));
        return ResponseEntity.ok(genres);
    }
    @PutMapping("{id}")
    public ResponseEntity<Genres> updateGenres(@PathVariable long id,@RequestBody Genres genresDetails) {
        Genres updateGenres = genresRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Genres existiert nicht mit id: " + id));
        updateGenres.setName(genresDetails.getName());
        Genres updatedGenres = genresRepository.save(updateGenres);
        return ResponseEntity.ok(updateGenres);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteGenres(@PathVariable long id){
        Genres genres = genresRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Genres existiert nicht mit id: " + id));
        genresRepository.delete(genres);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}