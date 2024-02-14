package com.uasz.enseign.repository.Maquette;

import com.uasz.enseign.model.Maquette.Niveau; // Importez l'entité plutôt que le DTO
import com.uasz.enseign.model.Maquette.Cycle;
import com.uasz.enseign.model.Maquette.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NiveauRepository extends JpaRepository<Niveau, Long> {

    List<Niveau> findByCycleId(Long cycleId);
}
