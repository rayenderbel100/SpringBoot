package tn.esprit.arctic.championnat.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.arctic.championnat.DTO.ContratDto;
import tn.esprit.arctic.championnat.Repository.ContratRepository;
import tn.esprit.arctic.championnat.Repository.EquipeRepository;
import tn.esprit.arctic.championnat.Repository.SponsorRepository;
import tn.esprit.arctic.championnat.entities.Contrat;
import tn.esprit.arctic.championnat.entities.Equipe;
import tn.esprit.arctic.championnat.entities.Sponsor;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class ContratService implements IContratService {

    private final ContratRepository contratRepository;
    private final EquipeRepository equipeRepository;
    private final SponsorRepository sponsorRepository;

    public ContratService(ContratRepository contratRepository,
                          EquipeRepository equipeRepository,
                          SponsorRepository sponsorRepository) {
        this.contratRepository = contratRepository;
        this.equipeRepository = equipeRepository;
        this.sponsorRepository = sponsorRepository;
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

    @Override
    public ContratDto ajoutContratEtAffecterASponsorEtEquipe(Contrat contrat, String libelleEquipe,
                                                              String nomSponsor, String pays) {
        Equipe equipe = equipeRepository.findByLibelle(libelleEquipe);
        Sponsor sponsor = sponsorRepository.findByNomAndPays(nomSponsor, pays);

        contrat.setEquipe(equipe);
        contrat.setSponsor(sponsor);
        Contrat savedContrat = contratRepository.save(contrat);

        // Mapping manuel vers ContratDto
        ContratDto dto = new ContratDto();
        dto.setIdContrat(savedContrat.getIdContrat());
        dto.setMontant(savedContrat.getMontant());
        dto.setAnnee(savedContrat.getAnnee());
        dto.setLibelleEquipe(equipe != null ? equipe.getLibelle() : null);
        dto.setNomSponsor(sponsor != null ? sponsor.getNom() : null);

        return dto;
    }
}
