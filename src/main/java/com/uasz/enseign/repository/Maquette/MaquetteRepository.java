package com.uasz.enseign.repository.Maquette;

import com.uasz.enseign.model.Maquette.Maquette; // Importez l'entité plutôt que le DTO
import com.uasz.enseign.model.Maquette.Formation;
import com.uasz.enseign.model.Maquette.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaquetteRepository extends JpaRepository<Maquette, Long> {

    List<Maquette> findByFormation_Id(Long formationId);

}
