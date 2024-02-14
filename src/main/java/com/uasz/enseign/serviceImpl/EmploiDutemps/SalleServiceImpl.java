package com.uasz.enseign.serviceImpl.EmploiDutemps;

import com.uasz.enseign.dto.EmploiDuTemps.SalleDTO;
import com.uasz.enseign.mapping.EmploiDuTemps.SalleMapper;
import com.uasz.enseign.model.Emploie_Du_Temps.Salle;
import com.uasz.enseign.repository.EmploiDuTemps.SalleRepository;
import com.uasz.enseign.service.EmploiDuTemps.SalleService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class SalleServiceImpl implements SalleService {

    private final SalleRepository salleRepository;
    private final SalleMapper salleMapper;

    @Autowired
    public SalleServiceImpl(SalleRepository salleRepository, SalleMapper salleMapper) {
        this.salleRepository = salleRepository;
        this.salleMapper = salleMapper;
    }

    @Override
    public List<SalleDTO> getAllSalles() {
        List<Salle> salles = salleRepository.findAll();
        return salles.stream()
                .map(salleMapper::fromSalle)
                .collect(Collectors.toList());
    }

    @Override
    public SalleDTO getSalleById(Long id) {
        Salle salle = salleRepository.findById(id).orElse(null);
        return (salle != null) ? salleMapper.fromSalle(salle) : null;
    }

    @Override
    public SalleDTO createSalle(SalleDTO salleDTO) {
        Salle salle = salleMapper.fromSalleDTO(salleDTO);
        salle = salleRepository.save(salle);
        return salleMapper.fromSalle(salle);
    }

    @Override
    public SalleDTO updateSalle(Long id, SalleDTO salleDTO) {
        Salle existingSalle = salleRepository.findById(id).orElse(null);
        if (existingSalle != null) {
            Salle updatedSalle = salleMapper.fromSalleDTO(salleDTO);
            updatedSalle.setId(existingSalle.getId());
            updatedSalle = salleRepository.save(updatedSalle);
            return salleMapper.fromSalle(updatedSalle);
        } else {
            return null;
        }
    }

    @Override
    public void deleteSalle(Long id) {
        salleRepository.deleteById(id);
    }
}
