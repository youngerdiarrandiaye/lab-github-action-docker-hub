package com.uasz.enseign.mapping.Maquette;
import com.uasz.enseign.dto.Maquette.EnseignementDTO;
import com.uasz.enseign.model.Maquette.Enseignement;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component

public class EnseignementMapper {
    public EnseignementDTO fromEnseignement (Enseignement enseignement){
         EnseignementDTO enseignementDTO = new EnseignementDTO();
        BeanUtils.copyProperties(enseignement,enseignementDTO);
        return  enseignementDTO;
    }
    public Enseignement fromEnseignementDTO(EnseignementDTO enseignementDTO){
        Enseignement enseignement = new Enseignement();
        BeanUtils.copyProperties(enseignementDTO, enseignement);
        return  enseignement;
    }
}
