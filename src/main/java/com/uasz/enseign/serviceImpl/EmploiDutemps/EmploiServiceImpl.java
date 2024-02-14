package com.uasz.enseign.serviceImpl.EmploiDutemps;

import com.uasz.enseign.dto.EmploiDuTemps.EmploiDTO;
import com.uasz.enseign.mapping.EmploiDuTemps.EmploiMapper;
import com.uasz.enseign.model.Emploie_Du_Temps.Emploi;
import com.uasz.enseign.repository.EmploiDuTemps.EmploiRepository;
import com.uasz.enseign.service.EmploiDuTemps.EmploiService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class EmploiServiceImpl implements EmploiService {

    private final EmploiRepository emploiRepository;
    private final EmploiMapper emploiMapper;

    @Autowired
    public EmploiServiceImpl(EmploiRepository emploiRepository, EmploiMapper emploiMapper) {
        this.emploiRepository = emploiRepository;
        this.emploiMapper = emploiMapper;
    }

    @Override
    public List<EmploiDTO> getAllEmplois() {
        List<Emploi> emplois = emploiRepository.findAll();
        return emplois.stream()
                .map(emploiMapper::fromEmploi)
                .collect(Collectors.toList());
    }

    @Override
    public EmploiDTO getEmploiById(Long id) {
        Emploi emploi = emploiRepository.findById(id).orElse(null);
        return (emploi != null) ? emploiMapper.fromEmploi(emploi) : null;
    }

    @Override
    public EmploiDTO createEmploi(EmploiDTO emploiDTO) {
        Emploi emploi = emploiMapper.fromEmploiDTO(emploiDTO);
        emploi = emploiRepository.save(emploi);
        return emploiMapper.fromEmploi(emploi);
    }

    @Override
    public EmploiDTO updateEmploi(Long id, EmploiDTO emploiDTO) {
        Emploi existingEmploi = emploiRepository.findById(id).orElse(null);
        if (existingEmploi != null) {
            Emploi updatedEmploi = emploiMapper.fromEmploiDTO(emploiDTO);
            updatedEmploi.setId(existingEmploi.getId());
            updatedEmploi = emploiRepository.save(updatedEmploi);
            return emploiMapper.fromEmploi(updatedEmploi);
        } else {
            return null;
        }
    }

    @Override
    public void deleteEmploi(Long id) {
        emploiRepository.deleteById(id);
    }
}
