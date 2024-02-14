package com.uasz.enseign.serviceImpl.Maquette;

import com.uasz.enseign.dto.Maquette.GroupeDTO;
import com.uasz.enseign.mapping.Maquette.GroupeMapper;
import com.uasz.enseign.model.Maquette.Classe;
import com.uasz.enseign.model.Maquette.Groupe;
import com.uasz.enseign.repository.Maquette.GroupeRepository;
import com.uasz.enseign.service.Maquette.GroupeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class GroupeServiceImpl implements GroupeService {

    private final GroupeRepository groupeRepository;
    private final GroupeMapper groupeMapper;

    @Autowired
    public GroupeServiceImpl(GroupeRepository groupeRepository, GroupeMapper groupeMapper) {
        this.groupeRepository = groupeRepository;
        this.groupeMapper = groupeMapper;
    }

    @Override
    public List<GroupeDTO> getAllGroupes() {
        List<Groupe> groupes = groupeRepository.findAll();
        return groupes.stream()
                .map(groupeMapper::fromGroupe)
                .collect(Collectors.toList());
    }

    @Override
    public GroupeDTO getGroupeById(Long id) {
        Optional<Groupe> groupe = groupeRepository.findById(id);
        return groupe.map(groupeMapper::fromGroupe).orElse(null);
    }

    @Override
    public GroupeDTO createGroupe(GroupeDTO groupeDTO) {
        Groupe groupe = groupeMapper.fromGroupeDTO(groupeDTO);
        Groupe savedGroupe = groupeRepository.save(groupe);
        return groupeMapper.fromGroupe(savedGroupe);
    }

    @Override
    public GroupeDTO updateGroupe(Long id, GroupeDTO groupeDTO) {
        Optional<Groupe> existingGroupeOptional = groupeRepository.findById(id);
        if (existingGroupeOptional.isPresent()) {
            Groupe existingGroupe = existingGroupeOptional.get();
            // Update fields from DTO
            existingGroupe.setLibelle(groupeDTO.getLibelle());
            // Save and return updated Groupe
            Groupe updatedGroupe = groupeRepository.save(existingGroupe);
            return groupeMapper.fromGroupe(updatedGroupe);
        } else {
            return null; // Groupe not found
        }
    }

    @Override
    public void deleteGroupe(Long id) {
        groupeRepository.deleteById(id);
    }

    @Override
    public List<GroupeDTO> getGroupesByClasseId(Long classeId) {
        Classe classe = new Classe(); // Créez une instance de Classe avec l'ID fourni
        List<Groupe> groupes = groupeRepository.findByClasse(classe);
        return groupes.stream()
                .map(groupeMapper::fromGroupe)
                .collect(Collectors.toList());
    }
}
