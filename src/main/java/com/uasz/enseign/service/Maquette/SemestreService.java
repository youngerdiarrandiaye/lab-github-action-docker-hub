package com.uasz.enseign.service.Maquette;

import com.uasz.enseign.dto.Maquette.SemestreDTO;
import com.uasz.enseign.model.Maquette.Classe;

import java.util.List;

public interface SemestreService {

    List<SemestreDTO> getAllSemestres();

    SemestreDTO getSemestreById(Long id);

    SemestreDTO createSemestre(SemestreDTO semestreDTO);

    SemestreDTO updateSemestre(Long id, SemestreDTO semestreDTO);

    void deleteSemestre(Long id);
//
//    List<SemestreDTO> getSemestresByClasseId(List<Classe> classes);

    List<SemestreDTO> getSemestresByModuleId(Long moduleId);

}
