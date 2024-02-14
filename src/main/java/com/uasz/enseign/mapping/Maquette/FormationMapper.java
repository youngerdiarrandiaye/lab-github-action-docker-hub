package com.uasz.enseign.mapping.Maquette;
import com.uasz.enseign.dto.Maquette.FormationDTO;
import com.uasz.enseign.model.Maquette.Formation;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
@Service
public class FormationMapper {
    public FormationDTO fromFormation(Formation formation) {
        FormationDTO formationDTO = new FormationDTO();
        BeanUtils.copyProperties(formation, formationDTO);
        return formationDTO;
    }
    public Formation fromFormationDTO(FormationDTO formationDTO) {
        Formation formation = new Formation();
        BeanUtils.copyProperties(formationDTO, formation);
        return formation;
    }
}

