package tn.esprit.arctic.championnat.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Championnat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChampionnat;

    @Enumerated(EnumType.STRING)
    private Categorie categorie;

    private String libelleC;
    private Integer annee;

    // OneToOne avec DetailChampionnat
    @OneToOne(cascade = CascadeType.ALL)
    private DetailChampionnat detailChampionnat;

    // OneToMany avec Course
    @OneToMany(mappedBy = "championnat", cascade = CascadeType.ALL)
    private Set<Course> courses;
}
