package com.uasz.enseign.model.Repartition;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@EqualsAndHashCode(callSuper=true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("VAC")
public class Vacataire extends Enseignant {
    private String specialite;
    private String contrat;
    private int heuresEnseignement;

}
