package tn.esprit.arctic.championnat.Services;

import tn.esprit.arctic.championnat.DTO.ContratDto;
import tn.esprit.arctic.championnat.entities.Contrat;

public interface IContratService {
    void archiverContratsExpireesEtAffichageContratsActifsParEquipe();

    ContratDto ajoutContratEtAffecterASponsorEtEquipe(Contrat contrat, String libelleEquipe,
                                                       String nomSponsor, String pays);
}
