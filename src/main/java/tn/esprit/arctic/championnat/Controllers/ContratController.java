package tn.esprit.arctic.championnat.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.arctic.championnat.DTO.ContratDto;
import tn.esprit.arctic.championnat.Services.IContratService;
import tn.esprit.arctic.championnat.entities.Contrat;

@RestController
@RequestMapping("/contrat")
public class ContratController {

    private final IContratService contratService;

    public ContratController(IContratService contratService) {
        this.contratService = contratService;
    }

    @PostMapping("/ajoutEtAffecter/{libelleEquipe}/{nomSponsor}/{pays}")
    public ResponseEntity<ContratDto> ajoutContratEtAffecterASponsorEtEquipe(
            @RequestBody Contrat contrat,
            @PathVariable String libelleEquipe,
            @PathVariable String nomSponsor,
            @PathVariable String pays) {
        return ResponseEntity.ok(
                contratService.ajoutContratEtAffecterASponsorEtEquipe(contrat, libelleEquipe, nomSponsor, pays));
    }
}
