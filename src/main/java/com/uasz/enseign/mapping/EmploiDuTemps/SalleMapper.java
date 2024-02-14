package com.uasz.enseign.mapping.EmploiDuTemps;
import com.uasz.enseign.dto.EmploiDuTemps.SalleDTO;
import com.uasz.enseign.model.Emploie_Du_Temps.Salle;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
@Service
public class SalleMapper {
    public SalleDTO fromSalle(Salle salle){
        SalleDTO salleDTO = new SalleDTO();
        BeanUtils.copyProperties(salle,salleDTO);
        return  salleDTO;
    }
    public Salle fromSalleDTO(SalleDTO salleDTO){
        Salle salle = new Salle();
        BeanUtils.copyProperties(salleDTO, salle);
        return  salle;
    }
}
