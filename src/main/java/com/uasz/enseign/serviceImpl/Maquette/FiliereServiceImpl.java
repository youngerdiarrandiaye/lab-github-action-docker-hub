package com.uasz.enseign.serviceImpl.Maquette;

import com.uasz.enseign.dto.Maquette.FiliereDTO;
import com.uasz.enseign.mapping.Maquette.FiliereMapper;
import com.uasz.enseign.model.Maquette.Filiere;
import com.uasz.enseign.repository.Maquette.FiliereRepository;
import com.uasz.enseign.service.Maquette.FiliereService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Slf4j

@Service
@Transactional
public class FiliereServiceImpl implements FiliereService {

    private final FiliereRepository filiereRepository;
    private final FiliereMapper filiereMapper;

    @Autowired
    public FiliereServiceImpl(FiliereRepository filiereRepository, FiliereMapper filiereMapper) {
        this.filiereRepository = filiereRepository;
        this.filiereMapper = filiereMapper;
    }

    @Override
    public List<FiliereDTO> getAllFilieres() {
        List<Filiere> filieres = filiereRepository.findAll();
        return filieres.stream()
                .map(filiereMapper::fromFiliere)
                .collect(Collectors.toList());
    }

    @Override
    public FiliereDTO getFiliereById(Long id) {
        Optional<Filiere> filiere = filiereRepository.findById(id);
        return filiere.map(filiereMapper::fromFiliere).orElse(null);
    }

    @Override
    public FiliereDTO createFiliere(FiliereDTO filiereDTO) {
        Filiere filiere = filiereMapper.fromFiliereDTO(filiereDTO);
        Filiere savedFiliere = filiereRepository.save(filiere);
        return filiereMapper.fromFiliere(savedFiliere);
    }

    @Override
    public FiliereDTO updateFiliere(Long id, FiliereDTO filiereDTO) {
        Optional<Filiere> existingFiliereOptional = filiereRepository.findById(id);
        if (existingFiliereOptional.isPresent()) {
            Filiere existingFiliere = existingFiliereOptional.get();
            // Update fields from DTO
            existingFiliere.setNom(filiereDTO.getNom());
            // Save and return updated Filiere
            Filiere updatedFiliere = filiereRepository.save(existingFiliere);
            return filiereMapper.fromFiliere(updatedFiliere);
        } else {
            return null; // Filiere not found
        }
    }

    @Override
    public void deleteFiliere(Long id) {
        filiereRepository.deleteById(id);
    }

    @Override
    public List<FiliereDTO> getFilieresByFormationId(Long formationId) {
        // Implémentez la logique pour récupérer les filières par ID de formation
        // Utilisez le repository pour interagir avec la base de données
        // Exemple : retournez une liste vide pour le moment
        List<Filiere> filieres = filiereRepository.findByFormationId(formationId);
        return filieres.stream()
                .map(filiereMapper::fromFiliere)
                .collect(Collectors.toList());
    }
}
