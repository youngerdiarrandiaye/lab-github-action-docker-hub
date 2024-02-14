package com.uasz.enseign.mapping.Maquette;
import com.uasz.enseign.dto.Maquette.NiveauDTO;
import com.uasz.enseign.model.Maquette.Niveau;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
@Service
public class NiveauMapper{
    public NiveauDTO fromNiveau(Niveau niveau) {
        NiveauDTO niveauDTO = new NiveauDTO();
        BeanUtils.copyProperties(niveau, niveauDTO);
        return niveauDTO;
    }
    public Niveau fromNiveauDTO(NiveauDTO niveauDTO) {
        Niveau niveau = new Niveau();
        BeanUtils.copyProperties(niveauDTO, niveau);
        return niveau;
    }
}
