package com.uasz.enseign.mapping.Repartition;
import com.uasz.enseign.dto.Repartition.VacataireDTO;
import com.uasz.enseign.model.Repartition.Vacataire;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
@Service
public class VacataireMapper {
    public VacataireDTO fromVacataire(Vacataire vacataire){
        VacataireDTO vacataireDTO = new VacataireDTO();
        BeanUtils.copyProperties(vacataire,vacataireDTO);
        return  vacataireDTO;
    }
    public Vacataire fromVacataireDTO(VacataireDTO vacataireDTO){
        Vacataire vacataire = new Vacataire();
        BeanUtils.copyProperties(vacataireDTO, vacataire);
        return  vacataire;
    }
}
