package com.uasz.enseign.repository.Maquette;

import com.uasz.enseign.dto.Maquette.ClasseDTO;
import com.uasz.enseign.model.Maquette.Classe;
import com.uasz.enseign.model.Maquette.UE;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClasseRepository extends CrudRepository<Classe, Long> {
    ClasseDTO findByid(Long id);
    Page<Classe> findByLibelleContains(String keyword, Pageable pageable);
}
