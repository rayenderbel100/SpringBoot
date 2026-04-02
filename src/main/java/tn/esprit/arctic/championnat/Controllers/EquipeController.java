package tn.esprit.arctic.championnat.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.arctic.championnat.Services.IEquipeService;
import tn.esprit.arctic.championnat.entities.Equipe;

import java.util.List;

@RestController
@RequestMapping("/equipe")
public class EquipeController {

    private final IEquipeService equipeService;

    public EquipeController(IEquipeService equipeService) {
        this.equipeService = equipeService;
    }

    @PostMapping
    public ResponseEntity<Equipe> ajouterEquipe(@RequestBody Equipe equipe) {
        return ResponseEntity.ok(equipeService.ajouterEquipe(equipe));
    }

    @PostMapping("/liste")
    public ResponseEntity<List<Equipe>> ajouterEquipes(@RequestBody List<Equipe> equipes) {
        return ResponseEntity.ok(equipeService.ajouterEquipes(equipes));
    }

    @PutMapping
    public ResponseEntity<Equipe> modifierEquipe(@RequestBody Equipe equipe) {
        return ResponseEntity.ok(equipeService.modifierEquipe(equipe));
    }

    @DeleteMapping("/{idEquipe}")
    public ResponseEntity<Void> supprimerEquipe(@PathVariable Long idEquipe) {
        equipeService.supprimerEquipe(idEquipe);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Equipe>> listEquipes() {
        return ResponseEntity.ok(equipeService.listEquipes());
    }

    @GetMapping("/{idEquipe}")
    public ResponseEntity<Equipe> recupererEquipe(@PathVariable Long idEquipe) {
        Equipe e = equipeService.recupererEquipe(idEquipe);
        if (e == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(e);
    }
}
