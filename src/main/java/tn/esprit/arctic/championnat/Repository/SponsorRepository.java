package tn.esprit.arctic.championnat.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.arctic.championnat.entities.Sponsor;

public interface SponsorRepository extends JpaRepository<Sponsor, Long> {

    Sponsor findByNomAndPays(String nom, String pays);
}
