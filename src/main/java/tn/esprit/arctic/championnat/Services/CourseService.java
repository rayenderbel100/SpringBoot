package tn.esprit.arctic.championnat.Services;

import org.springframework.stereotype.Service;
import tn.esprit.arctic.championnat.Repository.CourseRepository;
import tn.esprit.arctic.championnat.entities.Categorie;
import tn.esprit.arctic.championnat.entities.Course;

import java.time.LocalDate;
import java.util.List;

@Service
public class CourseService implements ICourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<String> getCoursesByCategorieForPilote(Categorie c, Long idPilote, LocalDate startDate, LocalDate endDate) {
        return courseRepository.findDistinctEmplacementByChampionnatCategorieAndPositionsPiloteIdPiloteAndDateCourseBetween(c, idPilote, startDate, endDate);
    }

    @Override
    public List<Course> listeCoursesParCategoriePourUnPiloteKeyword(Categorie categorie, String libelleP) {
        return courseRepository.findByChampionnatCategorieAndPositionsPiloteLibelleP(categorie, libelleP);
    }

    @Override
    public List<Course> listeCoursesParCategoriePourUnPiloteJPQL(Categorie categorie, String libelleP) {
        return courseRepository.listeCoursesParCategoriePourUnPiloteJPQL(categorie, libelleP);
    }

    @Override
    public List<String> listeEmplacementsCoursesParCategorieKeyword(Categorie categorie) {
        return courseRepository.findEmplacementByChampionnatCategorie(categorie);
    }

    @Override
    public List<String> listeEmplacementsCoursesParCategorieJPQL(Categorie categorie) {
        return courseRepository.listeEmplacementsCoursesParCategorieJPQL(categorie);
    }
}
