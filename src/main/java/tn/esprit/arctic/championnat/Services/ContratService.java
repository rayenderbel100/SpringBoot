package tn.esprit.arctic.championnat.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.arctic.championnat.Repository.ContratRepository;
import tn.esprit.arctic.championnat.entities.Contrat;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class ContratService implements IContratService {

    private final ContratRepository contratRepository;

    public ContratService(ContratRepository contratRepository) {
        this.contratRepository = contratRepository;
    }

    @Override
    @Scheduled(fixedRate = 30000)
    public void archiverContratsExpireesEtAffichageContratsActifsParEquipe() {
        int currentYear = LocalDate.now().getYear();
        List<Contrat> contrats = contratRepository.findAll();

        for (Contrat c : contrats) {
            if (c.getAnnee() != null && Integer.parseInt(c.getAnnee()) < currentYear) {
                if (c.getArchived() == null || !c.getArchived()) {
                    c.setArchived(true);
                    contratRepository.save(c);
                }
            }
        }

        for (Contrat c : contrats) {
            if (c.getArchived() == null || !c.getArchived()) {
                String equipeLibelle = (c.getEquipe() != null) ? c.getEquipe().getLibelle() : "Inconnue";
                String sponsorNom = (c.getSponsor() != null) ? c.getSponsor().getNom() : "Inconnu";
                
                log.info("L'équipe {} a un contrat d'un montant de {} avec le sponsor {}",
                        equipeLibelle, c.getMontant(), sponsorNom);
            }
        }
    }
}
