package com.uasz.enseign.model.Emploie_Du_Temps;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Deroulement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String matiere;
    private Date date;
    private String processus;
    @OneToOne
    private Seance seance;
}
