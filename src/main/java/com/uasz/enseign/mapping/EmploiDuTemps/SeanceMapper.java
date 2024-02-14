package com.uasz.enseign.mapping.EmploiDuTemps;
import com.uasz.enseign.dto.EmploiDuTemps.SeanceDTO;
import com.uasz.enseign.model.Emploie_Du_Temps.Seance;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
@Service
public class SeanceMapper {
    public SeanceDTO fromSeance(Seance seance){
        SeanceDTO seanceDTO = new SeanceDTO();
        BeanUtils.copyProperties(seance,seanceDTO);
        return  seanceDTO;
    }
    public Seance fromSeanceDTO(SeanceDTO seanceDTO){
         Seance seance = new Seance();
        BeanUtils.copyProperties(seanceDTO, seance);
        return  seance;
    }
}
