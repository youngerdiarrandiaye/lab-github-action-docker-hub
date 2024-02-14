package com.uasz.enseign.mapping.EmploiDuTemps;
import com.uasz.enseign.dto.EmploiDuTemps.EmploiDTO;
import com.uasz.enseign.model.Emploie_Du_Temps.Emploi;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
@Service
public class EmploiMapper{
    public EmploiDTO fromEmploi(Emploi emploi){
        EmploiDTO emploiDTO = new EmploiDTO();
        BeanUtils.copyProperties(emploi,emploiDTO);
        return  emploiDTO;
    }
    public Emploi fromEmploiDTO(EmploiDTO emploiDTO){
        Emploi emploi = new Emploi();
        BeanUtils.copyProperties(emploiDTO, emploi);
        return  emploi;
    }
}
