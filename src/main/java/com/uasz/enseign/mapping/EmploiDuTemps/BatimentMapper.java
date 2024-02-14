package com.uasz.enseign.mapping.EmploiDuTemps;

import com.uasz.enseign.dto.EmploiDuTemps.BatimentDTO;
import com.uasz.enseign.model.Emploie_Du_Temps.Batiment;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
@Service
public class BatimentMapper {

    public BatimentDTO fromBatiment(Batiment batiment){
        BatimentDTO batimentDTO = new BatimentDTO();
        BeanUtils.copyProperties(batiment, batimentDTO);
        return  batimentDTO;
    }
    public Batiment fromBatimentDTO(BatimentDTO batimentDTO){
        Batiment batiment = new Batiment();
        BeanUtils.copyProperties(batimentDTO, batiment);
        return  batiment;
    }
}
