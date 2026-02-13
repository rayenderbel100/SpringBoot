package tn.esprit.arctic.championnat.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.arctic.championnat.entities.Pilote;

public interface PiloteRepository extends JpaRepository<Pilote, Long> {
}
