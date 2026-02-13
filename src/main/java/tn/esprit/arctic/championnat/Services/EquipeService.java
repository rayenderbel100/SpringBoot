package tn.esprit.arctic.championnat.Services;

import org.springframework.stereotype.Service;
import tn.esprit.arctic.championnat.Repository.EquipeRepository;
import tn.esprit.arctic.championnat.entities.Equipe;

import java.util.List;

@Service
public class EquipeService implements IEquipeService {

    EquipeRepository er;

    // Injection par constructeur
    public EquipeService(EquipeRepository er) {
        this.er = er;
    }

    @Override
    public Equipe ajouterEquipe(Equipe equipe) {
        return er.save(equipe);
    }

    @Override
    public List<Equipe> ajouterEquipes(List<Equipe> equipes) {
        return er.saveAll(equipes);
    }

    @Override
    public Equipe modifierEquipe(Equipe equipe) {
        return er.save(equipe);
    }

    @Override
    public void supprimerEquipe(Long idEquipe) {
        er.deleteById(idEquipe);
    }

    @Override
    public List<Equipe> listEquipes() {
        return er.findAll();
    }

    @Override
    public Equipe recupererEquipe(Long idEquipe) {
        return er.findById(idEquipe).orElse(null);
    }
}
