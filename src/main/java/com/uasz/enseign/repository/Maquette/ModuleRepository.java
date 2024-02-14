package com.uasz.enseign.repository.Maquette;

import com.uasz.enseign.model.Maquette.Module; // Importez l'entité plutôt que le DTO
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {

    List<Module> findByUeId(Long ueId);

    List<Module> findByEcId(Long ecId);

    List<Module> findBySemestreId(Long semestreId);
}
