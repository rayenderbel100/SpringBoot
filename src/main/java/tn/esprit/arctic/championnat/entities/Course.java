package tn.esprit.arctic.championnat.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCourse;

    private String emplacement;
    private LocalDate dateCourse;

    @ManyToOne
    private Championnat championnat;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private Set<Position> positions;
}
