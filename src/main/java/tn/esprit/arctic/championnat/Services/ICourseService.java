package tn.esprit.arctic.championnat.Services;

import tn.esprit.arctic.championnat.entities.Categorie;
import tn.esprit.arctic.championnat.entities.Course;

import java.time.LocalDate;
import java.util.List;

public interface ICourseService {

    List<String> getCoursesByCategorieForPilote(Categorie c, Long idPilote, LocalDate startDate, LocalDate endDate);

    List<Course> listeCoursesParCategoriePourUnPiloteKeyword(Categorie categorie, String libelleP);

    List<Course> listeCoursesParCategoriePourUnPiloteJPQL(Categorie categorie, String libelleP);

    List<String> listeEmplacementsCoursesParCategorieKeyword(Categorie categorie);

    List<String> listeEmplacementsCoursesParCategorieJPQL(Categorie categorie);
}
