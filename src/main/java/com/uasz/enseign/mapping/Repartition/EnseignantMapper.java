package com.uasz.enseign.mapping.Repartition;

import com.uasz.enseign.dto.Repartition.EnseignantDTO;
import com.uasz.enseign.model.Repartition.Enseignant;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class EnseignantMapper {
    public EnseignantDTO fromEnseignant(Enseignant enseignant){
        EnseignantDTO enseignantDTO = new EnseignantDTO(){};
        BeanUtils.copyProperties(enseignant, enseignantDTO);
        return  enseignantDTO;
    }

    public Enseignant fromEnseignantDTO(EnseignantDTO enseignantDTO){
        Enseignant enseignant = new Enseignant(){};
        BeanUtils.copyProperties(enseignantDTO, enseignant);
        return  enseignant;
    }
}
