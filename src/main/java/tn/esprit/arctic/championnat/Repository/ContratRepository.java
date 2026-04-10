package tn.esprit.arctic.championnat.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.arctic.championnat.entities.Contrat;

public interface ContratRepository extends JpaRepository<Contrat, Long> {
}
