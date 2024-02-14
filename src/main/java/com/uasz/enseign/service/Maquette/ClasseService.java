package com.uasz.enseign.service.Maquette;
import com.uasz.enseign.dto.Maquette.ClasseDTO;
import com.uasz.enseign.dto.Maquette.UEDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClasseService {

    void createClasse(ClasseDTO classeDTO);

    void deleteClasse(Long id);

    //List<ClasseDTO> getAllClasses();
    Page<ClasseDTO> getAllClasses(String keyword, Pageable pageable);
    void updateClasse(ClasseDTO classeDTO);

    ClasseDTO getClasseById(Long classeId);
}
