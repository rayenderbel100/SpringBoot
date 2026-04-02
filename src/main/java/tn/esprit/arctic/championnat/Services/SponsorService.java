package tn.esprit.arctic.championnat.Services;

import org.springframework.stereotype.Service;
import tn.esprit.arctic.championnat.Repository.SponsorRepository;
import tn.esprit.arctic.championnat.entities.Sponsor;

import java.time.LocalDate;
import java.util.List;

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
}
