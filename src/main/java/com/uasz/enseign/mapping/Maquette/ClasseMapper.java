package com.uasz.enseign.mapping.Maquette;

import com.uasz.enseign.dto.EmploiDuTemps.BatimentDTO;
import com.uasz.enseign.dto.Maquette.ClasseDTO;
import com.uasz.enseign.model.Emploie_Du_Temps.Batiment;
import com.uasz.enseign.model.Maquette.Classe;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClasseMapper {
    public ClasseDTO  fromClasse(Classe classe){
        ClasseDTO classeDTO= new ClasseDTO();
        BeanUtils.copyProperties(classe,classeDTO);
        return classeDTO;
    }

    public Classe fromClasseDTO(ClasseDTO classeDTO){
        Classe classe = new Classe();
        BeanUtils.copyProperties(classeDTO,classe);
        return classe;
    }

    public List<ClasseDTO> toClasseDTOList(List<Classe> listClasses) {
        return null;
    }
}


