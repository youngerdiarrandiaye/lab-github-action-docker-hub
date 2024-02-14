package com.uasz.enseign.serviceImpl.Repartition;

import com.uasz.enseign.dto.Repartition.EnseignantDTO;
import com.uasz.enseign.dto.Repartition.VacataireDTO;
import com.uasz.enseign.model.Repartition.Vacataire;
import com.uasz.enseign.repository.Repartition.VacataireRepository;
import com.uasz.enseign.service.Repartition.VacataireService;
import com.uasz.enseign.mapping.Repartition.VacataireMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class VacataireServiceImpl implements VacataireService {

    private final VacataireRepository vacataireRepository;
    private final VacataireMapper vacataireMapper;

    @Autowired
    public VacataireServiceImpl(VacataireRepository vacataireRepository, VacataireMapper vacataireMapper) {
        this.vacataireRepository = vacataireRepository;
        this.vacataireMapper = vacataireMapper;
    }

    @Override
    public List<VacataireDTO> getAllVacataires() {
        List<Vacataire> vacataires = vacataireRepository.findAll();
        return vacataires.stream()
                .map(vacataireMapper::fromVacataire)
                .collect(Collectors.toList());
    }

    @Override
    public VacataireDTO getVacataireById(Long id) {
        Vacataire vacataire = vacataireRepository.findById(id).orElse(null);
        return (vacataire != null) ? vacataireMapper.fromVacataire(vacataire) : null;
    }

    @Override
    public VacataireDTO createVacataire(VacataireDTO vacataireDTO) {
        Vacataire vacataire = vacataireMapper.fromVacataireDTO(vacataireDTO);
        vacataire = vacataireRepository.save(vacataire);
        return vacataireMapper.fromVacataire(vacataire);
    }

    @Override
    public VacataireDTO updateVacataire(Long id, VacataireDTO vacataireDTO) {
        Vacataire existingVacataire = vacataireRepository.findById(id).orElse(null);
        if (existingVacataire != null) {
            Vacataire updatedVacataire = vacataireMapper.fromVacataireDTO(vacataireDTO);
            updatedVacataire.setId(existingVacataire.getId());
            updatedVacataire = vacataireRepository.save(updatedVacataire);
            return vacataireMapper.fromVacataire(updatedVacataire);
        } else {
            return null;
        }
    }

    @Override
    public void deleteVacataire(Long id) {
        vacataireRepository.deleteById(id);
    }

    @Override
    public List<EnseignantDTO> getAllEnseignants() {
        return null; // Implémentez la logique spécifique aux enseignants si nécessaire
    }

    @Override
    public EnseignantDTO getEnseignantById(Long id) {
        return null; // Implémentez la logique spécifique aux enseignants si nécessaire
    }

    @Override
    public EnseignantDTO createEnseignant(EnseignantDTO enseignantDTO) {
        return null; // Implémentez la logique spécifique aux enseignants si nécessaire
    }

    @Override
    public EnseignantDTO updateEnseignant(Long id, EnseignantDTO enseignantDTO) {
        return null; // Implémentez la logique spécifique aux enseignants si nécessaire
    }

    @Override
    public void deleteEnseignant(Long id) {
        // Implémentez la logique spécifique aux enseignants si nécessaire
    }
}
