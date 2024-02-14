package com.uasz.enseign.repository.Maquette;

import com.uasz.enseign.model.Maquette.Semestre; // Importez l'entité plutôt que le DTO
import com.uasz.enseign.model.Maquette.Classe;
import com.uasz.enseign.model.Maquette.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SemestreRepository extends JpaRepository<Semestre, Long> {
//
//    @Query("SELECT s FROM Semestre s WHERE :classParam MEMBER OF s.classes")
//    List<Semestre> findByClasses(@Param("classParam") Classe classe);

    List<Semestre> findByModulesId(Long moduleId);
}

