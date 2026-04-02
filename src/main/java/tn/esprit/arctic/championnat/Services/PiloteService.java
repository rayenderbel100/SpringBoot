package tn.esprit.arctic.championnat.Services;

import org.springframework.stereotype.Service;
import tn.esprit.arctic.championnat.Repository.PiloteRepository;
import tn.esprit.arctic.championnat.entities.Pilote;

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
}
