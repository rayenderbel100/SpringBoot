package tn.esprit.arctic.championnat.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.arctic.championnat.Services.ISponsorService;
import tn.esprit.arctic.championnat.entities.Sponsor;

import java.util.List;

@RestController
@RequestMapping("/sponsor")
public class SponsorController {

    private final ISponsorService sponsorService;

    public SponsorController(ISponsorService sponsorService) {
        this.sponsorService = sponsorService;
    }

    @PostMapping
    public ResponseEntity<Sponsor> ajouterSponsor(@RequestBody Sponsor sponsor) {
        return ResponseEntity.ok(sponsorService.ajouterSponsor(sponsor));
    }

    @PostMapping("/liste")
    public ResponseEntity<List<Sponsor>> ajouterSponsors(@RequestBody List<Sponsor> sponsors) {
        return ResponseEntity.ok(sponsorService.ajouterSponsors(sponsors));
    }

    @PutMapping
    public ResponseEntity<Sponsor> modifierSponsor(@RequestBody Sponsor sponsor) {
        return ResponseEntity.ok(sponsorService.modifierSponsor(sponsor));
    }

    @DeleteMapping("/{idSponsor}")
    public ResponseEntity<Void> supprimerSponsor(@PathVariable Long idSponsor) {
        sponsorService.supprimerSponsor(idSponsor);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Sponsor>> listSponsors() {
        return ResponseEntity.ok(sponsorService.listSponsors());
    }

    @GetMapping("/{idSponsor}")
    public ResponseEntity<Sponsor> recupererSponsor(@PathVariable Long idSponsor) {
        Sponsor s = sponsorService.recupererSponsor(idSponsor);
        if (s == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(s);
    }

    @PutMapping("/{idSponsor}/archiver")
    public ResponseEntity<Boolean> archiverSponsor(@PathVariable Long idSponsor) {
        Boolean result = sponsorService.archiverSponsor(idSponsor);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}
