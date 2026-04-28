package tn.esprit.arctic.championnat.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.arctic.championnat.entities.Championnat;

import java.util.List;

public interface ChampionnatRepository extends JpaRepository<Championnat, Long> {

    List<Championnat> findByAnneeGreaterThan(Integer annee);
}
