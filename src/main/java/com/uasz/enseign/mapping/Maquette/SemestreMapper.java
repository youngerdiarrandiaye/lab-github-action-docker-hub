package com.uasz.enseign.mapping.Maquette;

import com.uasz.enseign.dto.Maquette.SemestreDTO;
import com.uasz.enseign.model.Maquette.Semestre;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
@Service
public class SemestreMapper{

    public SemestreDTO fromSemestre(Semestre semestre) {
        SemestreDTO semestreDTO = new SemestreDTO();
        BeanUtils.copyProperties(semestre, semestreDTO);
        return semestreDTO;
    }
    public Semestre fromSemestreDTO(SemestreDTO semestreDTO) {
        Semestre semestre = new Semestre();
        BeanUtils.copyProperties(semestreDTO, semestre);
        return semestre;
    }
}