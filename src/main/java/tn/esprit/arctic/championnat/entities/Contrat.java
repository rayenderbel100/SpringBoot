package tn.esprit.arctic.championnat.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Contrat {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY )
    private long idContrat;
    private Float montant;
    private String annee;
    private Boolean archived;



}