package i.s.bbw.MusikProjekt.controller;


import i.s.bbw.MusikProjekt.model.Band;
import i.s.bbw.MusikProjekt.repository.BandRepository;
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
@RequestMapping("/api/v1/band")
public class BandController {
    @Autowired
    private BandRepository bandRepository;
    @GetMapping
    public List<Band> getAllBand(){
        return bandRepository.findAll();
    }
    // build create Role REST API
    @PostMapping
    public Band createBand(@RequestBody Band band) {
        return bandRepository.save(band);
    }
    @GetMapping("{id}")
    public ResponseEntity<Band> getBandById(@PathVariable  long id){
        Band band = bandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Band existiert nicht mit id:" + id));
        return ResponseEntity.ok(band);
    }
    @PutMapping("{id}")
    public ResponseEntity<Band> updateBand(@PathVariable long id,@RequestBody Band bandDetails) {
        Band updateBand = bandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Band existiert nicht mit id: " + id));
        updateBand.setName(bandDetails.getName());
        updateBand.setGruendungsjahr(bandDetails.getGruendungsjahr());
        Band updatedBand = bandRepository.save(updateBand);
        return ResponseEntity.ok(updateBand);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteBand(@PathVariable long id){
        Band band = bandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Band existiert nicht mit id: " + id));
        bandRepository.delete(band);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}