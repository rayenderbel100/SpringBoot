package tn.esprit.arctic.championnat.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pilote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPilote;

    private String libelleP;
    private Integer nbPointsTotal;
    private Integer classementGeneral;

    @ManyToOne
    private Equipe equipe;

    @OneToMany(mappedBy = "pilote")
    private Set<Position> positions;
}
