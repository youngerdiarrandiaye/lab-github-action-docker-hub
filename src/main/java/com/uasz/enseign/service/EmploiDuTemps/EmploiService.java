package com.uasz.enseign.service.EmploiDuTemps;

import com.uasz.enseign.dto.EmploiDuTemps.EmploiDTO;

import java.util.List;

public interface EmploiService {

    List<EmploiDTO> getAllEmplois();

    EmploiDTO getEmploiById(Long id);

    EmploiDTO createEmploi(EmploiDTO emploiDTO);

    EmploiDTO updateEmploi(Long id, EmploiDTO emploiDTO);

    void deleteEmploi(Long id);

}
