package tn.esprit.arctic.championnat.Services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.arctic.championnat.Repository.PiloteRepository;
import tn.esprit.arctic.championnat.entities.Pilote;

import java.time.LocalDate;
import java.util.List;

@Service
public class PiloteService implements IPiloteService {

    PiloteRepository pr;

    // constructeur pour injection
    public PiloteService(PiloteRepository pr) {
        this.pr = pr;
    }

    @Override
    public String addPilote(Pilote p) {
        Pilote saved = pr.save(p);
        return "Pilote ajouté avec succès, idPilote=" + saved.getIdPilote();
    }

    @Override
    public Pilote ajouterPilote(Pilote pilote) {
        return pr.save(pilote);
    }

    @Override
    public List<Pilote> ajouterPilotes(List<Pilote> pilotes) {
        return pr.saveAll(pilotes);
    }

    @Override
    public Pilote modifierPilote(Pilote pilote) {
        return pr.save(pilote);
    }

    @Override
    public void supprimerPilote(Long idPilote) {
        pr.deleteById(idPilote);
    }

    @Override
    public List<Pilote> listPilotes() {
        return pr.findAll();
    }

    @Override
    public Pilote recupererPilote(Long idPilote) {
        return pr.findById(idPilote).orElse(null);
    }

    @Override
    @Scheduled(cron = "0 15 11 31 12 *")
    public void mettreAJourPointsEtClassement() {
        int currentYear = LocalDate.now().getYear();
        List<Pilote> pilotes = pr.findAll();

        for (Pilote p : pilotes) {
            int sum = 0;
            if (p.getPositions() != null) {
                for (tn.esprit.arctic.championnat.entities.Position pos : p.getPositions()) {
                    if (pos.getCourse() != null && pos.getCourse().getDateCourse() != null) {
                        if (pos.getCourse().getDateCourse().getYear() == currentYear) {
                            if (pos.getNbPoints() != null) {
                                sum += pos.getNbPoints();
                            }
                        }
                    }
                }
            }
            p.setNbPointsTotal(sum);
        }

        List<Pilote> sortedPilotes = pilotes.stream()
                .sorted((p1, p2) -> {
                    int pts1 = p1.getNbPointsTotal() == null ? 0 : p1.getNbPointsTotal();
                    int pts2 = p2.getNbPointsTotal() == null ? 0 : p2.getNbPointsTotal();
                    return Integer.compare(pts2, pts1);
                })
                .toList();

        int currentRank = 1;
        for (Pilote p : sortedPilotes) {
            if (p.getNbPointsTotal() == null || p.getNbPointsTotal() == 0) {
                p.setClassementGeneral(0);
            } else {
                p.setClassementGeneral(currentRank);
                currentRank++;
            }
        }
        pr.saveAll(sortedPilotes);
    }
}
