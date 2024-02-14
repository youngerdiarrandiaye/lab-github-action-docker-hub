package com.uasz.enseign.serviceImpl.EmploiDutemps;

import com.uasz.enseign.dto.EmploiDuTemps.SeanceDTO;
import com.uasz.enseign.mapping.EmploiDuTemps.SeanceMapper;
import com.uasz.enseign.model.Emploie_Du_Temps.Seance;
import com.uasz.enseign.repository.EmploiDuTemps.SeanceRepository;
import com.uasz.enseign.service.EmploiDuTemps.SeanceService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional @Slf4j
public class SeanceServiceImpl implements SeanceService {

    private final SeanceRepository seanceRepository;
    private final SeanceMapper seanceMapper;

    @Autowired
    public SeanceServiceImpl(SeanceRepository seanceRepository, SeanceMapper seanceMapper) {
        this.seanceRepository = seanceRepository;
        this.seanceMapper = seanceMapper;
    }

    @Override
    public List<SeanceDTO> getAllSeances() {
        List<Seance> seances = seanceRepository.findAll();
        return seances.stream()
                .map(seanceMapper::fromSeance)
                .collect(Collectors.toList());
    }

    @Override
    public SeanceDTO getSeanceById(Long id) {
        Seance seance = seanceRepository.findById(id).orElse(null);
        return (seance != null) ? seanceMapper.fromSeance(seance) : null;
    }

    @Override
    public SeanceDTO createSeance(SeanceDTO seanceDTO) {
        Seance seance = seanceMapper.fromSeanceDTO(seanceDTO);
        seance = seanceRepository.save(seance);
        return seanceMapper.fromSeance(seance);
    }

    @Override
    public SeanceDTO updateSeance(Long id, SeanceDTO seanceDTO) {
        Seance existingSeance = seanceRepository.findById(id).orElse(null);
        if (existingSeance != null) {
            Seance updatedSeance = seanceMapper.fromSeanceDTO(seanceDTO);
            updatedSeance.setId(existingSeance.getId());
            updatedSeance = seanceRepository.save(updatedSeance);
            return seanceMapper.fromSeance(updatedSeance);
        } else {
            return null;
        }
    }

    @Override
    public void deleteSeance(Long id) {
        seanceRepository.deleteById(id);
    }
}
