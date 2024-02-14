package com.uasz.enseign.serviceImpl.Maquette;

import com.uasz.enseign.dto.Maquette.MaquetteDTO;
import com.uasz.enseign.mapping.Maquette.MaquetteMapper;
import com.uasz.enseign.model.Maquette.Formation;
import com.uasz.enseign.model.Maquette.Maquette;
import com.uasz.enseign.repository.Maquette.MaquetteRepository;
import com.uasz.enseign.service.Maquette.MaquetteService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Transactional
@Slf4j
@Service
public class MaquetteServiceImpl implements MaquetteService {

    private final MaquetteRepository maquetteRepository;
    private final MaquetteMapper maquetteMapper;

    @Autowired
    public MaquetteServiceImpl(MaquetteRepository maquetteRepository, MaquetteMapper maquetteMapper) {
        this.maquetteRepository = maquetteRepository;
        this.maquetteMapper = maquetteMapper;
    }

    @Override
    public List<MaquetteDTO> getAllMaquettes() {
        List<Maquette> maquettes = maquetteRepository.findAll();
        return maquettes.stream()
                .map(maquetteMapper::fromMaquette)
                .collect(Collectors.toList());
    }

    @Override
    public MaquetteDTO getMaquetteById(Long id) {
        Optional<Maquette> maquette = maquetteRepository.findById(id);
        return maquette.map(maquetteMapper::fromMaquette).orElse(null);
    }

    @Override
    public MaquetteDTO createMaquette(MaquetteDTO maquetteDTO) {
        Maquette maquette = maquetteMapper.fromMaquetteDTO(maquetteDTO);
        Maquette savedMaquette = maquetteRepository.save(maquette);
        return maquetteMapper.fromMaquette(savedMaquette);
    }

    @Override
    public MaquetteDTO updateMaquette(Long id, MaquetteDTO maquetteDTO) {
        Optional<Maquette> existingMaquetteOptional = maquetteRepository.findById(id);
        if (existingMaquetteOptional.isPresent()) {
            Maquette existingMaquette = existingMaquetteOptional.get();
            // Update fields from DTO
            existingMaquette.setCoefUe(maquetteDTO.getCoefUe());
            // Save and return updated Maquette
            Maquette updatedMaquette = maquetteRepository.save(existingMaquette);
            return maquetteMapper.fromMaquette(updatedMaquette);
        } else {
            return null; // Maquette not found
        }
    }

    @Override
    public void deleteMaquette(Long id) {
        maquetteRepository.deleteById(id);
    }

    @Override
    public List<MaquetteDTO> getMaquettesByFormationId(Long formationId) {
        Formation formation = new Formation(); // Créez une instance de Formation avec l'ID fourni
        List<Maquette> maquettes = maquetteRepository.findByFormation_Id(formationId);
        return maquettes.stream()
                .map(maquetteMapper::fromMaquette)
                .collect(Collectors.toList());
    }
}
