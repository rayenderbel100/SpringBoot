package tn.esprit.arctic.championnat.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Equipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipe;

    private String libelle;
    private Integer nbPointsTotal;
    private Integer classementGeneral;

    @OneToMany(mappedBy = "equipe", cascade = CascadeType.ALL)
    private Set<Pilote> pilotes;

    @OneToMany(mappedBy = "equipe", cascade = CascadeType.ALL)
    private Set<Contrat> contrats;
}
