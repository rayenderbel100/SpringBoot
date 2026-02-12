package tn.esprit.arctic.championnat.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetailChampionnat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetail;

    private String code;
    private String description;

    @OneToOne(mappedBy = "detailChampionnat")
    private Championnat championnat;
}
