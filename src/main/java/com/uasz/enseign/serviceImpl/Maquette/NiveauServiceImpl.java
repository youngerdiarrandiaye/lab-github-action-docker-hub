package com.uasz.enseign.serviceImpl.Maquette;

import com.uasz.enseign.dto.Maquette.NiveauDTO;
import com.uasz.enseign.mapping.Maquette.NiveauMapper;
import com.uasz.enseign.model.Maquette.Niveau;
import com.uasz.enseign.repository.Maquette.NiveauRepository;
import com.uasz.enseign.service.Maquette.NiveauService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional @Slf4j
public class NiveauServiceImpl implements NiveauService {

    private final NiveauRepository niveauRepository;
    private final NiveauMapper niveauMapper;

    @Autowired
    public NiveauServiceImpl(NiveauRepository niveauRepository, NiveauMapper niveauMapper) {
        this.niveauRepository = niveauRepository;
        this.niveauMapper = niveauMapper;
    }

    @Override
    public List<NiveauDTO> getAllNiveaux() {
        List<Niveau> niveaux = niveauRepository.findAll();
        return niveaux.stream()
                .map(niveauMapper::fromNiveau)
                .collect(Collectors.toList());
    }

    @Override
    public NiveauDTO getNiveauById(Long id) {
        return niveauRepository.findById(id)
                .map(niveauMapper::fromNiveau)
                .orElse(null);
    }

    @Override
    public NiveauDTO createNiveau(NiveauDTO niveauDTO) {
        Niveau niveau = niveauMapper.fromNiveauDTO(niveauDTO);
        niveau = niveauRepository.save(niveau);
        return niveauMapper.fromNiveau(niveau);
    }

    @Override
    public NiveauDTO updateNiveau(Long id, NiveauDTO niveauDTO) {
        return niveauRepository.findById(id)
                .map(existingNiveau -> {
                    // Update fields from DTO
                    existingNiveau.setNom(niveauDTO.getNom());
                    // Save and return updated Niveau
                    return niveauMapper.fromNiveau(niveauRepository.save(existingNiveau));
                })
                .orElse(null);
    }

    @Override
    public void deleteNiveau(Long id) {
        niveauRepository.deleteById(id);
    }

    @Override
    public List<NiveauDTO> getNiveauxByCycleId(Long cycleId) {
        List<Niveau> niveaux = niveauRepository.findByCycleId(cycleId);
        return niveaux.stream()
                .map(niveauMapper::fromNiveau)
                .collect(Collectors.toList());
    }
}
