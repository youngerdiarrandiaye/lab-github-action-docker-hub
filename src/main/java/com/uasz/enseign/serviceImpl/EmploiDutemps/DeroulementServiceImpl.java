package com.uasz.enseign.serviceImpl.EmploiDutemps;

import com.uasz.enseign.dto.EmploiDuTemps.DeroulementDTO;
import com.uasz.enseign.mapping.EmploiDuTemps.DeroulementMapper;
import com.uasz.enseign.model.Emploie_Du_Temps.Deroulement;
import com.uasz.enseign.repository.EmploiDuTemps.DeroulementRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uasz.enseign.service.EmploiDuTemps.DeroulementService;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class DeroulementServiceImpl implements DeroulementService {

    private final DeroulementRepository deroulementRepository;
    private final DeroulementMapper deroulementMapper;

    @Autowired
    public DeroulementServiceImpl(DeroulementRepository deroulementRepository, DeroulementMapper deroulementMapper) {
        this.deroulementRepository = deroulementRepository;
        this.deroulementMapper = deroulementMapper;
    }

    @Override
    public List<DeroulementDTO> getAllDeroulements() {
        List<Deroulement> deroulements = deroulementRepository.findAll();
        return deroulements.stream()
                .map(deroulementMapper::fromDeroulement)
                .collect(Collectors.toList());
    }

    @Override
    public DeroulementDTO getDeroulementById(Long id) {
        Deroulement deroulement = deroulementRepository.findById(id).orElse(null);
        return (deroulement != null) ? deroulementMapper.fromDeroulement(deroulement) : null;
    }

    @Override
    public DeroulementDTO createDeroulement(DeroulementDTO deroulementDTO) {
        Deroulement deroulement = deroulementMapper.fromDeroulementDTO(deroulementDTO);
        deroulement = deroulementRepository.save(deroulement);
        return deroulementMapper.fromDeroulement(deroulement);
    }

    @Override
    public DeroulementDTO updateDeroulement(Long id, DeroulementDTO deroulementDTO) {
        Deroulement existingDeroulement = deroulementRepository.findById(id).orElse(null);
        if (existingDeroulement != null) {
            Deroulement updatedDeroulement = deroulementMapper.fromDeroulementDTO(deroulementDTO);
            updatedDeroulement.setId(existingDeroulement.getId());
            updatedDeroulement = deroulementRepository.save(updatedDeroulement);
            return deroulementMapper.fromDeroulement(updatedDeroulement);
        } else {
            return null;
        }
    }

    @Override
    public void deleteDeroulement(Long id) {
        deroulementRepository.deleteById(id);
    }
}
