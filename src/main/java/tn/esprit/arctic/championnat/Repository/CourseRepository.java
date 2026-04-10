package tn.esprit.arctic.championnat.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.arctic.championnat.entities.Categorie;
import tn.esprit.arctic.championnat.entities.Course;

import java.time.LocalDate;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    // 4.1 Keyword
    List<String> findDistinctEmplacementByChampionnatCategorieAndPositionsPiloteIdPiloteAndDateCourseBetween(Categorie categorie, Long idPilote, LocalDate startDate, LocalDate endDate);

    // 4.2 Keyword
    List<Course> findByChampionnatCategorieAndPositionsPiloteLibelleP(Categorie categorie, String libelleP);

    // 4.3 JPQL
    @Query("SELECT c FROM Course c JOIN c.positions p WHERE c.championnat.categorie = :categorie AND p.pilote.libelleP = :libelleP")
    List<Course> listeCoursesParCategoriePourUnPiloteJPQL(@Param("categorie") Categorie categorie, @Param("libelleP") String libelleP);

    // 4.4 Keyword
    List<String> findEmplacementByChampionnatCategorie(Categorie categorie);

    // 4.5 JPQL
    @Query("SELECT c.emplacement FROM Course c WHERE c.championnat.categorie = :categorie")
    List<String> listeEmplacementsCoursesParCategorieJPQL(@Param("categorie") Categorie categorie);
}
