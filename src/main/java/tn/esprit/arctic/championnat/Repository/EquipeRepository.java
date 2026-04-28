package tn.esprit.arctic.championnat.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.arctic.championnat.entities.Equipe;

import java.util.List;

public interface EquipeRepository extends JpaRepository<Equipe, Long> {

    @Query("SELECT DISTINCT e FROM Equipe e JOIN e.contrats c JOIN e.pilotes p WHERE c.montant > :montantContrat AND p.classementGeneral <= 3")
    List<Equipe> listeEquipesContratSuperieurAXEtPiloteEnTop3(@Param("montantContrat") Float montantContrat);

    Equipe findByLibelle(String libelle);
}
