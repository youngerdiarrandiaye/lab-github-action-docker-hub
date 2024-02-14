package com.uasz.enseign.repository.Maquette;

import com.uasz.enseign.model.Maquette.Filiere; // Importez l'entité plutôt que le DTO
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
public interface FiliereRepository extends JpaRepository<Filiere, Long> {
    List<Filiere> findByFormationId(Long formationId);
}
