package com.uasz.enseign.repository.Maquette;

import com.uasz.enseign.model.Maquette.Formation; // Importez l'entité plutôt que le DTO
import com.uasz.enseign.model.Maquette.Filiere;
import com.uasz.enseign.model.Maquette.Maquette;
import com.uasz.enseign.model.Maquette.Classe;
import com.uasz.enseign.model.Maquette.Niveau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormationRepository extends JpaRepository<Formation, Long> {
    List<Formation> findByNiveau_Id(Long niveauId);
    List<Formation> findByFiliere_Id(Long filiereId);

}
