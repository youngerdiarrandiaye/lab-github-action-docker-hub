package com.uasz.enseign.model.Emploie_Du_Temps;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data @NoArgsConstructor @AllArgsConstructor
@Entity
public class Emploi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int duree;
    private Date debut;
    private Date fin;
    @OneToMany(mappedBy = "emploi")
    private List<Seance> seances;

}
