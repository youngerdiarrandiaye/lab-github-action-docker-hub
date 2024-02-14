package com.uasz.enseign.serviceImpl.Repartition;

import com.uasz.enseign.dto.Repartition.EnseignantDTO;
import com.uasz.enseign.mapping.Repartition.EnseignantMapper;
import com.uasz.enseign.model.Maquette.UE;
import com.uasz.enseign.model.Repartition.Enseignant;
import com.uasz.enseign.repository.Repartition.EnseignantRepository;
import com.uasz.enseign.service.Repartition.EnseignantService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class  EnseignantServiceImpl implements EnseignantService {
    private final EnseignantRepository enseignantRepository;
    private final EnseignantMapper enseignantMapper;
    @Autowired
    public EnseignantServiceImpl(EnseignantRepository enseignantRepository, EnseignantMapper enseignantMapper) {
        this.enseignantRepository = enseignantRepository;
        this.enseignantMapper = enseignantMapper;
    }
    @Override
    public List<EnseignantDTO> getAllEnseignants() {
        List<Enseignant> enseignants = enseignantRepository.findAll();
        return enseignants.stream()
                .map(enseignantMapper::fromEnseignant)
                .collect(Collectors.toList());
    }

    @Override
    public EnseignantDTO getEnseignantById(Long id) {
        Enseignant enseignant = enseignantRepository.findById(id).orElse(null);
        return (enseignant != null) ? enseignantMapper.fromEnseignant(enseignant) : null;
    }

    @Override
    public EnseignantDTO createEnseignant(EnseignantDTO enseignantDTO) {
        Enseignant enseignant = enseignantMapper.fromEnseignantDTO(enseignantDTO);
        enseignant = enseignantRepository.save(enseignant);
        return enseignantMapper.fromEnseignant(enseignant);
    }

    @Override
    public EnseignantDTO updateEnseignant(Long id, EnseignantDTO enseignantDTO) {
        Enseignant existingEnseignant = enseignantRepository.findById(id).orElse(null);
        if (existingEnseignant != null) {
            Enseignant updatedEnseignant = enseignantMapper.fromEnseignantDTO(enseignantDTO);
            updatedEnseignant.setId(existingEnseignant.getId());
            updatedEnseignant = enseignantRepository.save(updatedEnseignant);
            return enseignantMapper.fromEnseignant(updatedEnseignant);
        } else {
            return null;
        }
    }

    @Override
    public void deleteEnseignant(Long id) {
        enseignantRepository.deleteById(id);
    }
}
