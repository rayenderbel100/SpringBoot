package tn.esprit.arctic.championnat.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.arctic.championnat.entities.Equipe;

public interface EquipeRepository extends JpaRepository<Equipe, Long> {
}
