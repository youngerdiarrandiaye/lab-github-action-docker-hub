package com.uasz.enseign.mapping.Maquette;
import com.uasz.enseign.dto.Maquette.FiliereDTO;
import com.uasz.enseign.model.Maquette.Filiere;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
@Service
public class FiliereMapper {
    public FiliereDTO fromFiliere(Filiere filiere) {
        FiliereDTO filiereDTO = new FiliereDTO();
        BeanUtils.copyProperties(filiere, filiereDTO);
        return filiereDTO;
    }
    public Filiere fromFiliereDTO(FiliereDTO filiereDTO) {
        Filiere filiere = new Filiere();
        BeanUtils.copyProperties(filiereDTO, filiere);
        return filiere;
    }
}
