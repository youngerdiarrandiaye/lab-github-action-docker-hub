package com.uasz.enseign.mapping.Maquette;
import com.uasz.enseign.dto.Maquette.GroupeDTO;
import com.uasz.enseign.model.Maquette.Groupe;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
@Service
public class GroupeMapper {
    public GroupeDTO fromGroupe(Groupe groupe) {
        GroupeDTO groupeDTO = new GroupeDTO();
        BeanUtils.copyProperties(groupe, groupeDTO);
        return groupeDTO;
    }
    public Groupe fromGroupeDTO(GroupeDTO groupeDTO) {
        Groupe groupe = new Groupe();
        BeanUtils.copyProperties(groupeDTO, groupe);
        return groupe;
    }
}
