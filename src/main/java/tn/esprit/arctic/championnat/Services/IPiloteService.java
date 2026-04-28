package tn.esprit.arctic.championnat.Services;

import tn.esprit.arctic.championnat.DTO.PiloteDto;
import tn.esprit.arctic.championnat.entities.Pilote;
import java.util.List;

public interface IPiloteService {

    String addPilote(Pilote p);

    Pilote ajouterPilote(Pilote pilote);

    List<Pilote> ajouterPilotes(List<Pilote> pilotes);

    Pilote modifierPilote(Pilote pilote);

    void supprimerPilote(Long idPilote);

    List<Pilote> listPilotes();

    Pilote recupererPilote(Long idPilote);

    void mettreAJourPointsEtClassement();

    List<PiloteDto> listeWinners(Integer annee);
}
