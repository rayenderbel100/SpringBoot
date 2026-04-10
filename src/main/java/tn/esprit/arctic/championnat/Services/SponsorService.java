package tn.esprit.arctic.championnat.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.arctic.championnat.Repository.SponsorRepository;
import tn.esprit.arctic.championnat.entities.Sponsor;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class SponsorService implements ISponsorService {

    private final SponsorRepository sp;

    public SponsorService(SponsorRepository sp) {
        this.sp = sp;
    }

    @Override
    public Sponsor ajouterSponsor(Sponsor sponsor) {
        sponsor.setDateCreation(LocalDate.now());
        sponsor.setArchived(Boolean.FALSE);
        sponsor.setBloquerContrat(Boolean.FALSE);
        return sp.save(sponsor);
    }

    @Override
    public List<Sponsor> ajouterSponsors(List<Sponsor> sponsors) {
        LocalDate now = LocalDate.now();
        for (Sponsor s : sponsors) {
            s.setDateCreation(now);
            s.setArchived(Boolean.FALSE);
            s.setBloquerContrat(Boolean.FALSE);
        }
        return sp.saveAll(sponsors);
    }

    @Override
    public Sponsor modifierSponsor(Sponsor sponsor) {
        sponsor.setDateDerniereModification(LocalDate.now());
        return sp.save(sponsor);
    }

    @Override
    public void supprimerSponsor(Long idSponsor) {
        sp.deleteById(idSponsor);
    }

    @Override
    public List<Sponsor> listSponsors() {
        return sp.findAll();
    }

    @Override
    public Sponsor recupererSponsor(Long idSponsor) {
        return sp.findById(idSponsor).orElse(null);
    }

    @Override
    public Boolean archiverSponsor(Long idSponsor) {
        return sp.findById(idSponsor).map(sponsor -> {
            sponsor.setArchived(Boolean.TRUE);
            sp.save(sponsor);
            return sponsor.getArchived();
        }).orElse(null);
    }

    @Override
    public Float pourcentageBudgetAnnuelConsomme(Long idSponsor) {
        Sponsor sponsor = sp.findById(idSponsor).orElse(null);
        if (sponsor == null || sponsor.getBudgetAnnuel() == null || sponsor.getBudgetAnnuel() == 0f) {
            return 0f;
        }

        String anneeEnCours = String.valueOf(LocalDate.now().getYear());
        Float sum = 0f;

        if (sponsor.getContrats() != null) {
            for (tn.esprit.arctic.championnat.entities.Contrat c : sponsor.getContrats()) {
                if (anneeEnCours.equals(c.getAnnee())) {
                    sum += c.getMontant();
                }
            }
        }

        return (sum / sponsor.getBudgetAnnuel()) * 100;
    }

    @Override
    @Scheduled(cron = "0 0 9 * * MON")
    public void afficherEtVerifierSponsorsBudget() {
        List<Sponsor> sponsors = sp.findAll();
        for (Sponsor s : sponsors) {
            Float pourcentage = pourcentageBudgetAnnuelConsomme(s.getIdSponsor());
            log.info("sponsor: {} pourcentage : {}", s.getNom(), pourcentage);
            
            if (pourcentage > 70 && pourcentage < 100) {
                log.info("attention budget presque consommé : {} % !", pourcentage);
            } else if (pourcentage > 100) {
                log.info("budget dépassé!! vous ne pouvez plus faire de contrats");
                s.setBloquerContrat(true);
                sp.save(s);
            }
        }
    }
}
