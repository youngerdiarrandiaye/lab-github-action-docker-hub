package com.uasz.enseign.serviceImpl.Maquette;

import com.uasz.enseign.dto.Maquette.EnseignementDTO;
import com.uasz.enseign.mapping.Maquette.EnseignementMapper;
import com.uasz.enseign.model.Maquette.Enseignement;
import com.uasz.enseign.repository.Maquette.EnseignementRepository;
import com.uasz.enseign.service.Maquette.EnseignementService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class EnseignementServiceImpl implements EnseignementService {

    private final EnseignementRepository enseignementRepository;
    private final EnseignementMapper enseignementMapper;

    @Autowired
    public EnseignementServiceImpl(EnseignementRepository enseignementRepository, EnseignementMapper enseignementMapper) {
        this.enseignementRepository = enseignementRepository;
        this.enseignementMapper = enseignementMapper;
    }

    @Override
    public List<EnseignementDTO> getAllEnseignements() {
        List<Enseignement> enseignements = enseignementRepository.findAll();
        return enseignements.stream()
                .map(enseignementMapper::fromEnseignement)
                .collect(Collectors.toList());
    }

    @Override
    public EnseignementDTO getEnseignementById(Long id) {
        Enseignement enseignement = enseignementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enseignement not found with id: " + id));
        return enseignementMapper.fromEnseignement(enseignement);
    }

    @Override
    public EnseignementDTO createEnseignement(EnseignementDTO enseignementDTO) {
        Enseignement enseignement = enseignementMapper.fromEnseignementDTO(enseignementDTO);
        Enseignement savedEnseignement = enseignementRepository.save(enseignement);
        return enseignementMapper.fromEnseignement(savedEnseignement);
    }

    @Override
    public EnseignementDTO updateEnseignement(Long id, EnseignementDTO enseignementDTO) {
        Enseignement existingEnseignement = enseignementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enseignement not found with id: " + id));

        // Update properties of existingEnseignement with enseignementDTO
        BeanUtils.copyProperties(enseignementDTO, existingEnseignement, "id");

        Enseignement updatedEnseignement = enseignementRepository.save(existingEnseignement);
        return enseignementMapper.fromEnseignement(updatedEnseignement);
    }

    @Override
    public void deleteEnseignement(Long id) {
        enseignementRepository.deleteById(id);
    }
    @Override
    public List<EnseignementDTO> getEnseignementsByClasseId(Long classeId) {
        // Utiliser le repository pour rechercher les enseignements par classeId
        List<Enseignement> enseignementsByClasseId = enseignementRepository.findByClasse_Id(classeId);

        // Mapper les enseignements en DTO
        return enseignementsByClasseId.stream()
                .map(enseignementMapper::fromEnseignement)
                .collect(Collectors.toList());
    }

    @Override
    public List<EnseignementDTO> getEnseignementsByGroupeId(Long groupeId) {
        // Utiliser le repository pour rechercher les enseignements par groupeId
        List<Enseignement> enseignementsByGroupeId = enseignementRepository.findByGroupe_Id(groupeId);

        // Mapper les enseignements en DTO
        return enseignementsByGroupeId.stream()
                .map(enseignementMapper::fromEnseignement)
                .collect(Collectors.toList());
    }

}
