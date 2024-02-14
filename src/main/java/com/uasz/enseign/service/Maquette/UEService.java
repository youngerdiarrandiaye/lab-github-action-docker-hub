package com.uasz.enseign.service.Maquette;
import com.uasz.enseign.dto.Maquette.ModuleDTO;
import com.uasz.enseign.dto.Maquette.UEDTO;
import com.uasz.enseign.model.Maquette.EC;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UEService {

    UEDTO saveUE(UEDTO ueDTO);

    Page<UEDTO> afficherToutesUEs(String keyword, Pageable pageable);

    UEDTO afficherUE(Long idUE);

    UEDTO modifierUE(UEDTO ueDTO);

    void supprimerUE(Long idUE);

    List<EC> trouverEcsParUE(Long idUE);

//    List<ModuleDTO> trouverModulesParUE(Long idUE);
}
