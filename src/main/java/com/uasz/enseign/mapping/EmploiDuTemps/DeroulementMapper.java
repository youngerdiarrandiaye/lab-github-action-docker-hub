package com.uasz.enseign.mapping.EmploiDuTemps;
import com.uasz.enseign.dto.EmploiDuTemps.DeroulementDTO;
import com.uasz.enseign.model.Emploie_Du_Temps.Deroulement;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
@Service
public class DeroulementMapper {
    public DeroulementDTO fromDeroulement(Deroulement deroulement){
        DeroulementDTO deroulementDTO = new DeroulementDTO();
        BeanUtils.copyProperties(deroulement, deroulementDTO);
        return  deroulementDTO;
    }
    public Deroulement fromDeroulementDTO(DeroulementDTO deroulementDTO){
        Deroulement deroulement = new Deroulement();
        BeanUtils.copyProperties(deroulementDTO, deroulement);
        return  deroulement;
    }
}
