package tn.esprit.arctic.championnat.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sponsor {

    @Id
    private Long idSponsor;

    private String nom;

    private String pays;

    private Float budgetAnnuel;

    private Boolean bloquerContrat;

    Boolean archived;

    LocalDate dateCreation;

    LocalDate dateDerniereModification;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "sponsor")
    private List<Contrat> contrats;
}

