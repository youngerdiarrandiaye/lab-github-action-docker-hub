package com.uasz.enseign.serviceImpl.Maquette;

import com.uasz.enseign.dto.Maquette.FormationDTO;
import com.uasz.enseign.mapping.Maquette.FormationMapper;
import com.uasz.enseign.model.Maquette.Formation;
import com.uasz.enseign.repository.Maquette.FormationRepository;
import com.uasz.enseign.service.Maquette.FormationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j

public class FormationServiceImpl implements FormationService {

    private final FormationRepository formationRepository;
    private final FormationMapper formationMapper;

    @Autowired
    public FormationServiceImpl(FormationRepository formationRepository, FormationMapper formationMapper) {
        this.formationRepository = formationRepository;
        this.formationMapper = formationMapper;
    }

    @Override
    public List<FormationDTO> getAllFormations() {
        List<Formation> formations = formationRepository.findAll();
        return formations.stream()
                .map(formationMapper::fromFormation)
                .collect(Collectors.toList());
    }

    @Override
    public FormationDTO getFormationById(Long id) {
        Optional<Formation> formation = formationRepository.findById(id);
        return formation.map(formationMapper::fromFormation).orElse(null);
    }

    @Override
    public FormationDTO createFormation(FormationDTO formationDTO) {
        Formation formation = formationMapper.fromFormationDTO(formationDTO);
        Formation savedFormation = formationRepository.save(formation);
        return formationMapper.fromFormation(savedFormation);
    }

    @Override
    public FormationDTO updateFormation(Long id, FormationDTO formationDTO) {
        Optional<Formation> existingFormationOptional = formationRepository.findById(id);
        if (existingFormationOptional.isPresent()) {
            Formation existingFormation = existingFormationOptional.get();
            // Update fields from DTO
            existingFormation.setNom(formationDTO.getNom());
            // Save and return updated Formation
            Formation updatedFormation = formationRepository.save(existingFormation);
            return formationMapper.fromFormation(updatedFormation);
        } else {
            return null; // Formation not found
        }
    }

    @Override
    public void deleteFormation(Long id) {
        formationRepository.deleteById(id);
    }

    @Override
    public List<FormationDTO> getFormationsByFiliereId(Long filiereId) {
        // Utilisez le repository pour rechercher les formations par ID de filière
        List<Formation> formations = formationRepository.findByFiliere_Id(filiereId);

        // Mappez les entités Formation en DTO
        return formations.stream()
                .map(formationMapper::fromFormation)
                .collect(Collectors.toList());
    }

    @Override
    public List<FormationDTO> getFormationsByNiveauId(Long niveauId) {
        // Utilisez le repository pour rechercher les formations par ID de niveau
        List<Formation> formations = formationRepository.findByNiveau_Id(niveauId);

        // Mappez les entités Formation en DTO
        return formations.stream()
                .map(formationMapper::fromFormation)
                .collect(Collectors.toList());
    }
}