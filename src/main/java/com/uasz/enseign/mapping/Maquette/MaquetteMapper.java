package com.uasz.enseign.mapping.Maquette;
import com.uasz.enseign.dto.Maquette.MaquetteDTO;
import com.uasz.enseign.model.Maquette.Maquette;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
@Service
public class MaquetteMapper {
    public MaquetteDTO fromMaquette(Maquette maquette) {
        MaquetteDTO maquetteDTO = new MaquetteDTO();
        BeanUtils.copyProperties(maquette, maquetteDTO);
        return maquetteDTO;
    }
    public Maquette fromMaquetteDTO(MaquetteDTO maquetteDTO) {
        Maquette maquette = new Maquette();
        BeanUtils.copyProperties(maquetteDTO, maquette);
        return  maquette;
    }
}