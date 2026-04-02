package tn.esprit.arctic.championnat.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.arctic.championnat.Services.IPiloteService;
import tn.esprit.arctic.championnat.entities.Pilote;

import java.util.List;

@RestController
@RequestMapping("/pilote")
public class PiloteController {

    private final IPiloteService piloteService;

    public PiloteController(IPiloteService piloteService) {
        this.piloteService = piloteService;
    }

    @PostMapping
    public ResponseEntity<String> addPilote(@RequestBody Pilote p) {
        return ResponseEntity.ok(piloteService.addPilote(p));
    }

    @PostMapping("/crud")
    public ResponseEntity<Pilote> ajouterPilote(@RequestBody Pilote pilote) {
        return ResponseEntity.ok(piloteService.ajouterPilote(pilote));
    }

    @PostMapping("/liste")
    public ResponseEntity<List<Pilote>> ajouterPilotes(@RequestBody List<Pilote> pilotes) {
        return ResponseEntity.ok(piloteService.ajouterPilotes(pilotes));
    }

    @PutMapping
    public ResponseEntity<Pilote> modifierPilote(@RequestBody Pilote pilote) {
        return ResponseEntity.ok(piloteService.modifierPilote(pilote));
    }

    @DeleteMapping("/{idPilote}")
    public ResponseEntity<Void> supprimerPilote(@PathVariable Long idPilote) {
        piloteService.supprimerPilote(idPilote);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Pilote>> listPilotes() {
        return ResponseEntity.ok(piloteService.listPilotes());
    }

    @GetMapping("/{idPilote}")
    public ResponseEntity<Pilote> recupererPilote(@PathVariable Long idPilote) {
        Pilote p = piloteService.recupererPilote(idPilote);
        if (p == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(p);
    }
}
