package tn.esprit.arctic.championnat.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContratDto {
    private Long idContrat;
    private Float montant;
    private String annee;
    private String libelleEquipe;
    private String nomSponsor;
}
