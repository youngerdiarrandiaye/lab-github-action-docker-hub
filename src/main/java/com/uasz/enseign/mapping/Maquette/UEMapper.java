package com.uasz.enseign.mapping.Maquette;

import com.uasz.enseign.dto.Maquette.UEDTO;
import com.uasz.enseign.model.Maquette.UE;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
@Service
public class UEMapper {
    public UEDTO fromUE(UE ue) {
        UEDTO ueDTO = new UEDTO();
        BeanUtils.copyProperties(ue, ueDTO);
        return ueDTO;
    }
    public UE fromUEDTO(UEDTO ueDTO) {
        UE ue = new UE();
        BeanUtils.copyProperties(ueDTO, ue);
        return ue;
    }
}



